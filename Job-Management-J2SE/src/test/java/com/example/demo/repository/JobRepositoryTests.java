package com.example.demo.repository;

import com.example.demo.model.Job;
import com.example.demo.model.enumeration.Status;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

/**
 * Tests for the JPA Job Repository.
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public class JobRepositoryTests {

    @Autowired
    private JobRepository jobRepository;

    /**
     * Tests if a job is saved correctly in the database.
     */
    @Test
    public void testSaveJob(){
        Job job = new Job("send email","Sends an email", LocalDateTime.now(), Status.QUEUED,"Test Action");
        Long jobId = this.jobRepository.save(job).getId();
        Job job2 = this.jobRepository.findById(jobId).get();

        Assert.assertNotNull(job);
        Assert.assertEquals(job.getId(),job2.getId());
        Assert.assertEquals(job.getCreatedAt(),job2.getCreatedAt());
        Assert.assertEquals(job.getAction(),job2.getAction());
        Assert.assertEquals(job.getName(),job2.getName());
        Assert.assertEquals(job.getDescription(),job2.getDescription());
        Assert.assertEquals(job.getStatus(),job2.getStatus());
    }

    /**
     * Tests if a job is deleted correctly in the database.
     */
    @Test
    public void testDeleteJob(){
        Job job = new Job("send email","Sends an email", LocalDateTime.now(), Status.QUEUED,"Test Action");
        Long jobId = this.jobRepository.save(job).getId();
        this.jobRepository.delete(job);

        Assert.assertThrows(NoSuchElementException.class, () -> {
                this.jobRepository.findById(jobId).get();
        });
    }

    /**
     * Tests the find all jobs method.
     */
    @Test
    public void testFindAllJobs(){
        Job job1 = new Job("send email","Sends an email", LocalDateTime.now(), Status.QUEUED,"Test Action");
        Job job2 = new Job("send email","Sends an email", LocalDateTime.now(), Status.QUEUED,"Test Action");
        Job job3 = new Job("send email","Sends an email", LocalDateTime.now(), Status.QUEUED,"Test Action");

        this.jobRepository.save(job1);
        this.jobRepository.save(job2);
        this.jobRepository.save(job3);

        Assert.assertNotNull(this.jobRepository.findAll());
        Assert.assertEquals(3,this.jobRepository.findAll().stream().count());
    }


}
