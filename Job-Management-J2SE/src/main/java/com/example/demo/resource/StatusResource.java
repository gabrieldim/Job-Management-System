package com.example.demo.resource;

import com.example.demo.model.enumeration.Status;

/**
 * Job status resource used to generate JSON response.
 */
public class StatusResource {

    private Long jobId;
    private String jobName;
    private Status jobStatus;

    public StatusResource(Long jobId, String jobName, Status jobStatus) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobStatus = jobStatus;
    }

    public StatusResource() {
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Status getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Status jobStatus) {
        this.jobStatus = jobStatus;
    }
}
