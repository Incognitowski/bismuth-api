package com.bismuth.bismuth.project.visibility

import com.bismuth.bismuth.project.ProjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.transaction.Transactional

@Transactional
@RestController
@RequestMapping("/project/{projectId}/visibility")
class ProjectVisibilityController {

    @Autowired
    lateinit var projectVisibilityService: ProjectVisibilityService;

    @Autowired
    lateinit var projectService: ProjectService;


    @DeleteMapping("/{projectVisibilityId}")
    fun removeUserFromProject(
            @PathVariable("projectId") projectId: UUID,
            @PathVariable("projectVisibilityId") projectVisibilityId: UUID
    ): ProjectVisibility {
        return projectService.detachUserFromProject(projectId, projectVisibilityId);
    }

}