package com.bismuth.bismuth.project

import com.bismuth.bismuth.framework.authentication.Auth
import com.bismuth.bismuth.project.events.ProjectEvent
import com.bismuth.bismuth.project.events.ProjectEventService
import com.bismuth.bismuth.project.visibility.ProjectVisibility
import com.bismuth.bismuth.project.visibility.ProjectVisibilityENUM
import com.bismuth.bismuth.project.visibility.ProjectVisibilityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.transaction.Transactional

@RestController
@RequestMapping("/project")
class ProjectController {

    @Autowired
    lateinit var projectService: ProjectService;

    @Autowired
    lateinit var projectVisibilityService: ProjectVisibilityService;

    @Autowired
    lateinit var projectEventService: ProjectEventService;

    @Autowired
    lateinit var request: HttpServletRequest;

    @PostMapping
    @Transactional
    fun createProject(
            @RequestBody project: Project
    ): Project {
        val user = Auth.getAuthenticatedUser(request);
        val createdProject = projectService.create(project, user);
        projectVisibilityService.create(ProjectVisibility(
                null,
                project.project_id!!,
                user.user_id!!,
                "OWNER"
        ));
        projectEventService.create(ProjectEvent(
                null,
                "User ${user.username} created the '${project.name}' project. Let's get to work!",
                project.project_id!!,
                user.user_id!!
        ));
        return createdProject;
    }


}