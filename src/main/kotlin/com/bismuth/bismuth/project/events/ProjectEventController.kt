package com.bismuth.bismuth.project.events

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/project/{projectId}/events")
class ProjectEventController {

    @Autowired
    lateinit var projectEventService: ProjectEventService;

    @GetMapping
    fun getEventsRelatedToProject(
            @PathVariable("projectId") projectId: UUID
    ): List<ProjectEvent> {
        return projectEventService.getAllFromProjectWithUsers(projectId);
    };

}