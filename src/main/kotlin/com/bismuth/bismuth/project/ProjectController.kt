package com.bismuth.bismuth.project

import com.bismuth.bismuth.project.events.ProjectEvent
import com.bismuth.bismuth.project.visibility.ProjectVisibility
import com.bismuth.bismuth.project.visibility.ProjectVisibilityService
import com.bismuth.bismuth.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.transaction.Transactional

@Transactional
@RestController
@RequestMapping("/project")
class ProjectController {

    @Autowired
    lateinit var projectService: ProjectService;

    @PostMapping
    fun createProject(
            @RequestBody project: Project
    ): Project {
        return projectService.create(project);
    }

    @PutMapping("/{projectId}")
    fun updateProject(
            @PathVariable("projectId") projectId: UUID,
            @RequestBody project: Project
    ): Project {
        return projectService.update(project, projectId);
    }

    @GetMapping("/{projectId}")
    fun getProjectById(
            @PathVariable("projectId") projectId: UUID
    ): Project {
        return projectService.getById(projectId);
    }

    @GetMapping("/{projectId}/users")
    fun getUsersRelatedToProject(
            @PathVariable("projectId") projectId: UUID
    ): List<User> {
        return projectService.getUsersRelatedToProject(projectId);
    }

    @GetMapping("/search-by-name/{projectName}")
    fun getProjectById(
            @PathVariable("projectName") projectName: String
    ): List<Project> {
        return projectService.searchByDescription(projectName);
    }

    @GetMapping
    fun getAllVisibleProjects(pageable: Pageable): Page<Project> {
        return projectService.getAllVisibleForUser(pageable)
    }

    @DeleteMapping("/{projectId}")
    fun deleteProject(
            @PathVariable("projectId") projectId: UUID
    ) {
        projectService.delete(projectId);
    }

    @PostMapping("/{projectId}/users")
    fun attachUserToProject(
            @PathVariable("projectId") projectId: UUID,
            @RequestBody projectVisibility: ProjectVisibility
    ) : ProjectVisibility {
        return projectService.attachUserToProject(projectId, projectVisibility);
    }

    @PutMapping("/{projectId}/transfer")
    fun transferProjectOwnership(
            @PathVariable("projectId") projectId: UUID,
            @RequestBody projectTransferPOKO : ProjectTransferPOKO
    ) {
        return projectService.transferProjectOwnership(projectId, projectTransferPOKO)
    }

}