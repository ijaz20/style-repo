package com.style.scheduler.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import com.style.Constants;
import com.style.exception.AppException;
import com.style.scheduler.service.SchedulerManager;
import com.style.scheduler.service.SchedulerService;
import com.style.util.StringUtil;

/**
 * Implementation of SchedulerService interface.
 * 
 * @author mathi
 */
@Service("schedulerManager")
public class SchedulerManagerImpl implements SchedulerManager, SchedulerService {

	private MessageSourceAccessor messageSource;

	@Autowired
	public void setMessages(MessageSource messageSource) {
		this.messageSource = new MessageSourceAccessor(messageSource);
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, Object> scheduleJob(Map<String, Object> schedulerDetails)
			throws AppException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String groupName = Constants.DEFAULT_SCHEDULER_GROUP;
		try {
			if (StringUtil.isEmptyString(schedulerDetails.get("groupName"))) {
				schedulerDetails.put("groupName", groupName);
			}
			if (validateJobDetails(schedulerDetails)) {
				// schedule it
				Scheduler scheduler = new StdSchedulerFactory().getScheduler();
				scheduler.start();
				scheduler.scheduleJob(getNewSchedulerJob(schedulerDetails),
						getNewSchedulerTrigger(schedulerDetails));
				responseMap.put("message", messageSource.getMessage(
						"job.scheduled",
						new Object[] { schedulerDetails.get("jobName") }));
			}
		} catch (SchedulerException e) {
			throw new AppException(messageSource.getMessage("errors.schedule",
					new Object[] { schedulerDetails.get("jobName") }));
		}
		return responseMap;
	}

	/**
	 * validate the empty parameters
	 * 
	 * @param schedulerDetails
	 * @return
	 * @throws AppException
	 */
	public boolean validateJobDetails(Map<String, Object> schedulerDetails)
			throws AppException {
		StringBuffer errors = new StringBuffer();
		if (StringUtil.isEmptyString(schedulerDetails.get("jobName"))) {
			errors.append("Job Name");
		}
		if (StringUtil.isEmptyString(schedulerDetails.get("jobClass"))) {
			errors.append(", Job Class");
		}
		if (StringUtil.isEmptyString(schedulerDetails.get("triggerName"))) {
			errors.append(", Trigger Name");
		}

		if (StringUtil.isEmptyString(errors.toString())) {
			return true;
		} else {
			throw new AppException(messageSource.getMessage("errors.not.empty",
					new Object[] { errors.toString() }));
		}
	}

	/**
	 * get the scheduler job
	 * 
	 * @param schedulerDetails
	 * @return
	 * @throws AppException
	 */
	@SuppressWarnings("unchecked")
	public JobDetail getNewSchedulerJob(Map<String, Object> schedulerDetails)
			throws AppException {
		try {
			Class<?> jobClass = Class.forName(schedulerDetails.get("jobClass")
					.toString());
			JobDetail job = JobBuilder
					.newJob((Class<? extends Job>) jobClass)
					.withIdentity(schedulerDetails.get("jobName").toString(),
							schedulerDetails.get("groupName").toString())
					.build();
			return job;
		} catch (ClassNotFoundException e) {
			throw new AppException(messageSource.getMessage(
					"errors.job.class.notFound",
					new Object[] { schedulerDetails.get("jobClass") }));
		}
	}

	/**
	 * get the trigger to schedule the job
	 * 
	 * @param schedulerDetails
	 * @return
	 * @throws AppException
	 */
	public Trigger getNewSchedulerTrigger(Map<String, Object> schedulerDetails)
			throws AppException {
		if (!StringUtil.isEmptyString(schedulerDetails.get("triggerType"))
				&& schedulerDetails.get("triggerType").toString()
						.equalsIgnoreCase("cron")) {
			return getCronTrigger(schedulerDetails);
		} else {
			return getTimeTrigger(schedulerDetails);
		}
	}

	/**
	 * get the trigger for cron expression
	 * 
	 * @param schedulerDetails
	 * @return
	 * @throws AppException
	 */
	public Trigger getCronTrigger(Map<String, Object> schedulerDetails)
			throws AppException {
		if (!StringUtil.isEmptyString(schedulerDetails.get("cronExpression"))) {
			// configure the scheduler time
			Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity(
							schedulerDetails.get("triggerName").toString(),
							schedulerDetails.get("groupName").toString())
					.withSchedule(
							CronScheduleBuilder.cronSchedule(schedulerDetails
									.get("cronExpression").toString())).build();
			return trigger;

		} else {
			throw new AppException(
					messageSource.getMessage("errors.empty.cronExpression"));
		}
	}

	/**
	 * get the trigger for timing interval
	 * 
	 * @param schedulerDetails
	 * @return
	 * @throws AppException
	 */
	public Trigger getTimeTrigger(Map<String, Object> schedulerDetails)
			throws AppException {
		if (!StringUtil.isEmptyString(schedulerDetails.get("interval"))) {
			// configure the scheduler time
			Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity(
							schedulerDetails.get("triggerName").toString(),
							schedulerDetails.get("groupName").toString())
					.withSchedule(
							SimpleScheduleBuilder
									.simpleSchedule()
									.withIntervalInMilliseconds(
											Long.valueOf(schedulerDetails.get(
													"interval").toString()))
									.repeatForever()).build();
			return trigger;
		} else {
			throw new AppException(
					messageSource.getMessage("errors.empty.interval"));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, Object> shutdownScheluer() throws AppException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.shutdown(true);
			responseMap.put("message",
					messageSource.getMessage("scheduler.stopped"));
			return responseMap;
		} catch (SchedulerException e) {
			throw new AppException(
					messageSource.getMessage("errors.schedule.shutdown"));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> rescheduleJob(
			Map<String, Object> schedulerDetails) throws AppException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Trigger newTrigger = null;
		String groupName = Constants.DEFAULT_SCHEDULER_GROUP;
		try {
			if (!StringUtil.isEmptyString(schedulerDetails.get("groupName"))) {
				groupName = schedulerDetails.get("groupName").toString();
			}
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			if (!StringUtil.isEmptyString(schedulerDetails.get("triggerName"))) {
				// retrieve the trigger
				Trigger oldTrigger = scheduler.getTrigger(new TriggerKey(
						schedulerDetails.get("triggerName").toString(),
						groupName));
				newTrigger = getRescheduledTrigger(oldTrigger, schedulerDetails);
				scheduler.rescheduleJob(oldTrigger.getKey(), newTrigger);
				responseMap.put("message",
						messageSource.getMessage("job.rescheduled"));
				return responseMap;
			} else {
				throw new AppException(
						messageSource.getMessage("errors.empty.triggerName"));
			}
		} catch (SchedulerException e) {
			throw new AppException(
					messageSource.getMessage("errors.reschedule"));
		}
	}

	/**
	 * get rescheduled trigger
	 * 
	 * @param oldTrigger
	 * @param schedulerDetails
	 * @return
	 * @throws AppException
	 */
	public Trigger getRescheduledTrigger(Trigger oldTrigger,
			Map<String, Object> schedulerDetails) throws AppException {
		TriggerBuilder triggerBuilder = oldTrigger.getTriggerBuilder();

		if (!StringUtil.isEmptyString(schedulerDetails.get("triggerType"))
				&& schedulerDetails.get("triggerType").toString()
						.equalsIgnoreCase("cron")) {
			return getCronTrigger(triggerBuilder, schedulerDetails);
		} else {
			return getTimeTrigger(triggerBuilder, schedulerDetails);
		}
	}

	/**
	 * get cron trigger
	 * 
	 * @param triggerBuilder
	 * @param schedulerDetails
	 * @return
	 * @throws AppException
	 */
	@SuppressWarnings("unchecked")
	public Trigger getCronTrigger(TriggerBuilder triggerBuilder,
			Map<String, Object> schedulerDetails) throws AppException {
		if (!StringUtil.isEmptyString(schedulerDetails.get("cronExpression"))) {
			// configure the scheduler time
			Trigger trigger = triggerBuilder.withSchedule(
					CronScheduleBuilder.cronSchedule(schedulerDetails.get(
							"cronExpression").toString())).build();
			return trigger;

		} else {
			throw new AppException(
					messageSource.getMessage("errors.empty.cronExpression"));
		}
	}

	/**
	 * get time interval trigger
	 * 
	 * @param triggerBuilder
	 * @param schedulerDetails
	 * @return
	 * @throws AppException
	 */
	@SuppressWarnings("unchecked")
	public Trigger getTimeTrigger(TriggerBuilder triggerBuilder,
			Map<String, Object> schedulerDetails) throws AppException {
		if (!StringUtil.isEmptyString(schedulerDetails.get("interval"))) {
			Trigger trigger = triggerBuilder.withSchedule(
					SimpleScheduleBuilder
							.simpleSchedule()
							.withIntervalInMilliseconds(
									Long.valueOf(schedulerDetails.get(
											"interval").toString()))
							.repeatForever()).build();
			return trigger;
		} else {
			throw new AppException(
					messageSource.getMessage("errors.empty.interval"));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, Object> unscheduleJob(
			Map<String, Object> schedulerDetails) throws AppException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String groupName = Constants.DEFAULT_SCHEDULER_GROUP;
		try {
			if (!StringUtil.isEmptyString(schedulerDetails.get("groupName"))) {
				groupName = schedulerDetails.get("groupName").toString();
			}
			if (!StringUtil.isEmptyString(schedulerDetails.get("triggerName"))) {
				Scheduler scheduler = new StdSchedulerFactory().getScheduler();
				// Unschedule a particular trigger from the job (a job may have
				// more than one trigger)
				scheduler.unscheduleJob(new TriggerKey(schedulerDetails.get(
						"triggerName").toString(), groupName));
				responseMap.put("message",
						messageSource.getMessage("job.unscheduled"));
				return responseMap;
			} else {
				throw new AppException(
						messageSource.getMessage("errors.empty.triggerName"));
			}
		} catch (SchedulerException e) {
			throw new AppException(
					messageSource.getMessage("errors.unschedule"));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, Object> deleteJob(Map<String, Object> schedulerDetails)
			throws AppException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String groupName = Constants.DEFAULT_SCHEDULER_GROUP;
		try {
			if (!StringUtil.isEmptyString(schedulerDetails.get("groupName"))) {
				groupName = schedulerDetails.get("groupName").toString();
			}
			if (!StringUtil.isEmptyString(schedulerDetails.get("jobName"))) {
				Scheduler scheduler = new StdSchedulerFactory().getScheduler();
				// Schedule the job with the trigger
				scheduler.deleteJob(new JobKey(schedulerDetails.get("jobName")
						.toString(), groupName));
				responseMap.put("message",
						messageSource.getMessage("job.deleted"));
				return responseMap;
			} else {
				throw new AppException(
						messageSource.getMessage("errors.empty.jobName"));
			}
		} catch (SchedulerException e) {
			throw new AppException(
					messageSource.getMessage("errors.job.delete"));
		}
	}
}
