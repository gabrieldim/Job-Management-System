package com.example.demo.factory;

import com.example.demo.model.Job;
import com.example.demo.resource.StatusResource;

/**
 * Job Factory Mapper.
 */
public class JobFactoryMapper {

    /**
     * Maps a Job to a status resource.
     *
     * @param job the given Job object.
     * @return {@link StatusResource}
     */
    public static StatusResource mapToStatusResource(Job job) {

        StatusResource statusResource = new StatusResource(job.getId(), job.getName(), job.getStatus());

        return statusResource;
    }

}
