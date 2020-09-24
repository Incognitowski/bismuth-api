package com.bismuth.bismuth.httpAPI

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.util.*

@Transactional
@RestController
@RequestMapping("project/{projectId}/application/{applicationId}/http-api")
class HttpAPIController {

    @Autowired
    lateinit var httpAPIService: HttpAPIService;

    @PostMapping
    fun create(
            @PathVariable("projectId") projectId: UUID,
            @PathVariable("applicationId") applicationId: UUID,
            @RequestBody httpAPI: HttpAPI
    ): HttpAPI = httpAPIService.create(projectId, applicationId, httpAPI);

    @GetMapping
    fun getAllVisibleToUser(
            @PathVariable("projectId") projectId: UUID,
            @PathVariable("applicationId") applicationId: UUID
    ): List<HttpAPI> = httpAPIService.getAllVisibleToUser(projectId, applicationId);

}