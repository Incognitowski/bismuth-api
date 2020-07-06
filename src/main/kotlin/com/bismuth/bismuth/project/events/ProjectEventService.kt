package com.bismuth.bismuth.project.events

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProjectEventService {

    @Autowired
    lateinit var projectEventRepository: ProjectEventRepository;

    fun create(projectEvent: ProjectEvent): ProjectEvent {
        projectEvent.project_event_id = UUID.randomUUID();
        //todo validate
        return projectEventRepository.save(projectEvent);
    }

}