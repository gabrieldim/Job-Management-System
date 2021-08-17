package com.example.demo.controller;

import com.example.demo.factory.JobFactoryMapper;
import com.example.demo.model.Job;
import com.example.demo.repository.JobRepository;
import com.example.demo.resource.StatusResource;
import com.example.demo.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * This is a REST Controller used for interaction with the Job Management System.
 */
@RestController
@RequestMapping("/jobs")
public class JobsController {

    private final JobService jobService;

    public JobsController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/addJob")
    public ResponseEntity addJob(@RequestBody Job job){
        job.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.ok(this.jobService.save(job));
    }

    @PostMapping("/startJob/{id}")
    public ResponseEntity startJob(@PathVariable Long id) {
        this.jobService.runJob(id);
        return ResponseEntity.ok("Job Started");
    }

    @GetMapping("/checkStatus/{id}")
    public ResponseEntity checkJobStatus(@PathVariable Long id) {
        Job job = this.jobService.findById(id);
        StatusResource statusResource = JobFactoryMapper.mapToStatusResource(job);
        return ResponseEntity.ok(statusResource);
    }

    @GetMapping("/getAllJobs")
    public ResponseEntity getAllJobs(){
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping("/scheduleJob/{id}")
    public ResponseEntity scheduleJob(@PathVariable Long id,
                                      @RequestParam Long delayInSeconds) {
        jobService.runScheduledJob(id, delayInSeconds);
        return ResponseEntity.ok("Job Scheduled");
    }

    @PostMapping("/scheduleJobPeriodically/{id}")
    public ResponseEntity scheduleJobPeriodically(@PathVariable Long id,
                                                  @RequestParam Long delayInSeconds,
                                                  @RequestParam Long repeatPeriod) {

        jobService.runScheduledJobPeriodically(id, delayInSeconds, repeatPeriod);
        return ResponseEntity.ok("Job Scheduled");
    }

}
