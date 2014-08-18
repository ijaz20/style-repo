package com.style.scheduler.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Sample job for schedule
 * 
 * @author mathi
 * 
 */
@DisallowConcurrentExecution
public class SampleJob implements Job {
    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        System.out
                .println("Hello Quartz------------------------------------------!");

    }

}
