package br.com.dan.contactsimporter.jobs.listener;


import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Starting job {}", jobExecution.getJobInstance().getJobName());
    }
    
    @Override
    public void afterJob(JobExecution jobExecution) {
        if (BatchStatus.COMPLETED.equals(jobExecution.getStatus())) {
            log.info("Job execution completed");
        }
    }

}
