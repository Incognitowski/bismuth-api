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
        val listOfEventsToCreate: List<String> = mutableListOf();
        if (project.isPubliclyVisible != originalProject.isPubliclyVisible) {
            listOfEventsToCreate.plus(
                    if (project.isPubliclyVisible)
                        "User ${user.username} made the '${project.name}' project visible to the public."
                    else
                        "User ${user.username} made the '${project.name}' project private, only authorized accounts have access to it now."
            )
        }
        if (originalProject.name != project.name) {
            listOfEventsToCreate.plus(
                    "User ${user.username} changed the name of this project. It used to be '${originalProject.name}', but now is '${project.name}'."
            )
        }
        if (originalProject.active != project.active) {
            listOfEventsToCreate.plus(
                    if (project.active)
                        "User ${user.username} reactivated the '${project.name}' project. Good to have it back 😁"
                    else
                        "User ${user.username} deactivated the '${project.name}' project. It'll be missed 😔"
            )
        }
        if (originalProject.isSoftdeleted != project.isSoftdeleted) {
            listOfEventsToCreate.plus(
                    "User ${user.username} deleted the '${project.name}' project. RIP '${project.name}' project 😞"
            )
        }
        for (event: String in listOfEventsToCreate) {
            create(ProjectEvent(
                    null,
                    event,
                    project.projectId!!,
                    user.userId
            ));
        }
    }

    fun createNewProjectEvent(project: Project) {
        val user = Auth.getAuthenticatedUser(request);
        create(ProjectEvent(
                null,
                "User ${user.username} created the '${project.name}' project. Let's get to work! 🛠",
                project.projectId!!,
                user.userId!!
        ));
    }

}