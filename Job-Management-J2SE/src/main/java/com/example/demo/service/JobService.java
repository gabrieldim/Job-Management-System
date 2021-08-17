package com.example.demo.service;

import com.example.demo.model.Job;
import com.example.demo.model.enumeration.Status;
import com.example.demo.repository.JobRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Timer;

/**
 * This is the business logic of the application.
 */
@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job save(Job job) {
        job.setStatus(Status.QUEUED);
        return this.jobRepository.save(job);
    }

    public Job findById(Long id) {
        return this.jobRepository.findById(id).get();
    }

    public List<Job> findAll() {
        return this.jobRepository.findAll();
    }


    /**
     * This method schedules a Job to run in the future.
     *
     * @param id the Id of the Job.
     * @param delay the starting delay in seconds.
     */
    @Async
    public void runScheduledJob(Long id, Long delay){
        ScheduledTask scheduledTask = new ScheduledTask(this, id);
        Timer t = new Timer();
        Long delayInMilliseconds = delay*1000;
        t.schedule(scheduledTask,delayInMilliseconds);
    }

    /**
     * This method schedules a Job to run periodically in the future.
     *
     * @param id the Id of the Job.
     * @param delay the starting delay in seconds.
     * @param repeatPeriod the repeating period in seconds.
     */
    @Async
    public void runScheduledJobPeriodically(Long id, Long delay, Long repeatPeriod){
        ScheduledTask scheduledTask = new ScheduledTask(this, id);
        Timer t = new Timer();
        Long delayInMilliseconds = delay * 1000;
        Long repeatPeriodMilliseconds = repeatPeriod * 1000;
        t.scheduleAtFixedRate(scheduledTask, delayInMilliseconds,repeatPeriodMilliseconds);
    }

    /**
     * This method runs a Job with a given Id immediately.
     *
     * @param id the Id of the Job.
     */
    @Async
    public void runJob(Long id){
        Job job = this.jobRepository.findById(id).get();
        job.setStatus(Status.RUNNING);
        this.jobRepository.save(job);
        /* Assumption: Every created job that is running is calling external API to do the defined job.
        *  Assumption: If the API returns a failed state, the Job status should be set as FAILED.
        *  Assumption: It could be implemented in try/catch block or if statements.
        * */
        try {
            Thread.sleep(10000); /* Simulating time spent by the job. */
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        job.setStatus(Status.SUCCESS);
        this.jobRepository.save(job);
    }

}
