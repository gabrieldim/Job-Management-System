package com.example.demo.service;

import java.util.TimerTask;


public class ScheduledTask extends TimerTask {

    private final JobService jobService;
    private Long jobId;

    public ScheduledTask(JobService jobService, Long jobId) {
        this.jobService = jobService;
        this.jobId = jobId;
    }

    @Override
    public void run() {
        jobService.runJob(jobId);
    }

}