package com.bismuth.bismuth.application

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.transaction.Transactional

@Transactional
@RestController
@RequestMapping("project/{projectId}/application")
class ApplicationController {

    @Autowired
    lateinit var applicationService: ApplicationService;

    @GetMapping("/{applicationId}")
    fun getById(
            @PathVariable("projectId") projectId: UUID,
            @PathVariable("applicationId") applicationId: UUID
    ): Application {
        return applicationService.getById(applicationId);
    }

    @PostMapping
    fun create(
            @PathVariable("projectId") projectId: UUID,
            @RequestBody application: Application
    ): Application {
        application.projectId = projectId;
        return applicationService.create(application);
    }

    @PutMapping("/{applicationId}")
    fun update(
            @PathVariable("projectId") projectId: UUID,
            @PathVariable("applicationId") applicationId: UUID,
            @RequestBody application: Application
    ): Application {
        return applicationService.update(application, applicationId);
    }

    @GetMapping
    fun getAppsByProjectId(
            @PathVariable("projectId") projectId: UUID,
            pageable: Pageable
    ): Page<Application> {
        return applicationService.getAllWithProjectId(projectId, pageable);
    }

    @GetMapping("search-by-name/{searchWord}")
    fun searchAppsByName(
            @PathVariable("projectId") projectId: UUID,
            @PathVariable("searchWord") searchWord: String
    ): List<Application> {
        return applicationService.searchAppsByName(projectId, searchWord);
    }


}