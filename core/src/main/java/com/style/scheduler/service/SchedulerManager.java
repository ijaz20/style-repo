package com.style.scheduler.service;

import java.util.Map;

import com.style.exception.AppException;

/**
 * Web Service interface for manage schedules.
 * 
 * @author mathi
 */

public interface SchedulerManager {

	/**
	 * schedule the job
	 * 
	 * @param schedulerDetails
	 * @throws AppException
	 */
	Map<String, Object> scheduleJob(Map<String, Object> schedulerDetails)
			throws AppException;

	/**
	 * shutdown scheduler
	 * 
	 * @return
	 * 
	 * @throws AppException
	 */
	Map<String, Object> shutdownScheluer() throws AppException;

	/**
	 * reschedule the job
	 * 
	 * @param schedulerDetails
	 * @throws AppException
	 */
	Map<String, Object> rescheduleJob(Map<String, Object> schedulerDetails)
			throws AppException;

	/**
	 * unschedule job
	 * 
	 * @param schedulerDetails
	 * @throws AppException
	 */
	Map<String, Object> unscheduleJob(Map<String, Object> schedulerDetails)
			throws AppException;

	/**
	 * delete job from schedule
	 * 
	 * @param schedulerDetails
	 * @throws AppException
	 */
	Map<String, Object> deleteJob(Map<String, Object> schedulerDetails)
			throws AppException;
}
