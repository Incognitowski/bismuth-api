package com.bismuth.bismuth.project.visibility

import com.bismuth.bismuth.framework.authentication.Auth
import com.bismuth.bismuth.project.Project
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
class ProjectVisibilityService {

    @Autowired
    lateinit var request: HttpServletRequest;

    @Autowired
    lateinit var projectVisibilityRepository: ProjectVisibilityRepository;

    fun create(projectVisibility: ProjectVisibility): ProjectVisibility {
        projectVisibility.project_visibility_id = UUID.randomUUID();
        return projectVisibilityRepository.save(projectVisibility);
    }

    fun createNewProjectVisibility(project: Project) {
        val user = Auth.getAuthenticatedUser(request);
        create(ProjectVisibility(
                null,
                project.project_id!!,
                user.user_id!!,
                "OWNER"
        ));
    }

}