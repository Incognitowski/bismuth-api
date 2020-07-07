package com.bismuth.bismuth.project

import org.springframework.beans.factory.annotation.Autowired
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

    @GetMapping("/search-by-name/{projectName}")
    fun getProjectById(
            @PathVariable("projectName") projectName: String
    ): List<Project> {
        return projectService.searchByDescription(projectName);
    }

    @GetMapping
    fun getAllVisibleProjects(): List<Project> = projectService.getAllVisibleForUser()

    @DeleteMapping("/{projectId}")
    fun deleteProject(
            @PathVariable("projectId") projectId: UUID
    ) {
        projectService.delete(projectId);
    }

}