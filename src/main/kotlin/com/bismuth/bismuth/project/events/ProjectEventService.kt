package com.bismuth.bismuth.project.events

import com.bismuth.bismuth.framework.authentication.Auth
import com.bismuth.bismuth.project.Project
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
class ProjectEventService {

    @Autowired
    lateinit var request: HttpServletRequest;

    @Autowired
    lateinit var projectEventRepository: ProjectEventRepository;

    fun create(projectEvent: ProjectEvent): ProjectEvent {
        projectEvent.project_event_id = UUID.randomUUID();
        return projectEventRepository.save(projectEvent);
    }

    fun createEventsForUpdate(project: Project, originalProject: Project) {
        val user = Auth.getAuthenticatedUser(request);
        if (project.isPubliclyVisible != originalProject.isPubliclyVisible) {
            val changedVisibilityEventString = if (project.isPubliclyVisible)
                "User ${user.username} made the '${project.name}' project visible to the public."
            else
                "User ${user.username} made the '${project.name}' project private, only authorized accounts have access to it now.";
            create(ProjectEvent(
                    null,
                    changedVisibilityEventString,
                    project.project_id!!,
                    user.user_id
            ));
        }
        if (originalProject.name != project.name) {
            val changedProjectNameEventString =
                    "User ${user.username} changed the name of this project. It used to be '${originalProject.name}', buw not is '${project.name}'."
            create(ProjectEvent(
                    null,
                    changedProjectNameEventString,
                    project.project_id!!,
                    user.user_id
            ));
        }
        if (originalProject.active != project.active) {
            val changedProjectStateEventString = if (project.active)
                "User ${user.username} reactivated the '${project.name}' project. Good to have it back 😁"
            else
                "User ${user.username} deactivated the '${project.name}' project. It'll be missed 😔";
            create(ProjectEvent(
                    null,
                    changedProjectStateEventString,
                    project.project_id!!,
                    user.user_id
            ));
        }
        if (originalProject.isSoftdeleted != project.isSoftdeleted) {
            val deletedEventString =
                    "User ${user.username} deleted the '${project.name}' project. RIP '${project.name}' project 😞";
            create(ProjectEvent(
                    null,
                    deletedEventString,
                    project.project_id!!,
                    user.user_id
            ));
        }
    }

    fun createNewProjectEvent(project: Project) {
        val user = Auth.getAuthenticatedUser(request);
        create(ProjectEvent(
                null,
                "User ${user.username} created the '${project.name}' project. Let's get to work! 🛠",
                project.project_id!!,
                user.user_id!!
        ));
    }

}