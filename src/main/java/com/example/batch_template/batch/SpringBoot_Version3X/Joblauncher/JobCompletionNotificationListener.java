package com.example.batch_template.batch.SpringBoot_Version3X.Joblauncher;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  @Override
  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
    	System.out.println("BATCH JOB(" + jobExecution.getJobInstance().getJobName() + ") FINISHED SUCCESSFULLY");
    }
  }

}
