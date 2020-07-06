package com.bismuth.bismuth.project.visibility

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProjectVisibilityService {

    @Autowired
    lateinit var projectVisibilityRepository: ProjectVisibilityRepository;

    fun create(projectVisibility: ProjectVisibility): ProjectVisibility {
        projectVisibility.project_visibility_id = UUID.randomUUID();
        //todo validate
        return projectVisibilityRepository.save(projectVisibility);
    }

}