package com.example.demo.repository;

import com.example.demo.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is a JPA Repository working with the Job entity.
 */
@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

}
