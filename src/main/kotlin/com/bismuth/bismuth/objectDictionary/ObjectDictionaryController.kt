package com.bismuth.bismuth.objectDictionary

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("project/{projectId}/application/{applicationId}/object-dictionary")
class ObjectDictionaryController {

    @Autowired
    lateinit var objectDictionaryService: ObjectDictionaryService;

    @GetMapping
    fun getAllVisibleToUser(
            @PathVariable("projectId") projectId: UUID,
            @PathVariable("applicationId") applicationId: UUID
    ): List<ObjectDictionary> = objectDictionaryService.getAllVisibleToUser(projectId, applicationId);

    @PostMapping
    fun create(
            @PathVariable("projectId") projectId: UUID,
            @PathVariable("applicationId") applicationId: UUID,
            @RequestBody objectDictionary: ObjectDictionary
    ): ObjectDictionary = objectDictionaryService.create(projectId, applicationId, objectDictionary);


}