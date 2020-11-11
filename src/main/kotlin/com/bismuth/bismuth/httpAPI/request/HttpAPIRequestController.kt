package com.bismuth.bismuth.httpAPI.request

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.util.*

@Transactional
@RestController
@RequestMapping("project/{projectId}/application/{applicationId}/http-api/{httpAPIId}/request")
class HttpAPIRequestController {

    @Autowired
    lateinit var httpAPIRequestService: HttpAPIRequestService;

    @GetMapping
    fun getAllByHttpAPI(
            @PathVariable("projectId") projectId: UUID,
            @PathVariable("applicationId") applicationId: UUID,
            @PathVariable("httpAPIId") httpAPIId: UUID
    ): List<HttpAPIRequest> = httpAPIRequestService.getAllFromHttpAPI(httpAPIId);

    @GetMapping("/search-by-word/{searchWord}")
    fun searchRequestsByWord(
            @PathVariable("projectId") projectId: UUID,
            @PathVariable("applicationId") applicationId: UUID,
            @PathVariable("httpAPIId") httpAPIId: UUID,
            @PathVariable("searchWord") searchWord: String
    ): List<HttpAPIRequest> = httpAPIRequestService.searchByWordInHttpAPI(httpAPIId, searchWord);


    @PostMapping
    fun create(
            @PathVariable("projectId") projectId: UUID,
            @PathVariable("applicationId") applicationId: UUID,
            @PathVariable("httpAPIId") httpAPIId: UUID,
            @RequestBody httpAPIRequest: HttpAPIRequest
    ): HttpAPIRequest = httpAPIRequestService.create(httpAPIId, httpAPIRequest);

    @PutMapping("/{httpAPIRequestId}")
    fun update(
            @PathVariable("projectId") projectId: UUID,
            @PathVariable("applicationId") applicationId: UUID,
            @PathVariable("httpAPIId") httpAPIId: UUID,
            @PathVariable("httpAPIRequestId") httpAPIRequestId: UUID,
            @RequestBody httpAPIRequest: HttpAPIRequest
    ): HttpAPIRequest = httpAPIRequestService.update(httpAPIRequestId, httpAPIRequest);

}