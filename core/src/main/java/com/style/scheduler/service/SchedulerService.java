package com.style.scheduler.service;

import java.util.Map;

import javax.jws.WebService;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.style.exception.AppException;

/**
 * Web Service interface for manage schedules.
 * 
 * @author mathi
 */
@WebService
@Path("/scheduler")
public interface SchedulerService {

	/**
	 * schedule the job
	 * 
	 * @param schedulerDetails
	 * @throws AppException
	 */
	@POST
	@Path("/scheduleJob")
	@Produces("application/json")
	Map<String, Object> scheduleJob(Map<String, Object> schedulerDetails)
			throws AppException;

	/**
	 * shutdown scheduler
	 * 
	 * @return
	 * 
	 * @throws AppException
	 */
	@POST
	@Path("/shutdownSchedule")
	@Produces("application/json")
	Map<String, Object> shutdownScheluer() throws AppException;

	/**
	 * reschedule the job
	 * 
	 * @param schedulerDetails
	 * @throws AppException
	 */
	@POST
	@Path("/rescheduleJob")
	@Produces("application/json")
	Map<String, Object> rescheduleJob(Map<String, Object> schedulerDetails)
			throws AppException;

	/**
	 * unschedule job
	 * 
	 * @param schedulerDetails
	 * @throws AppException
	 */
	@POST
	@Path("/unscheduleJob")
	@Produces("application/json")
	Map<String, Object> unscheduleJob(Map<String, Object> schedulerDetails)
			throws AppException;

	/**
	 * delete job from schedule
	 * 
	 * @param schedulerDetails
	 * @throws AppException
	 */
	@POST
	@Path("/deleteJob")
	@Produces("application/json")
	Map<String, Object> deleteJob(Map<String, Object> schedulerDetails)
			throws AppException;
}
