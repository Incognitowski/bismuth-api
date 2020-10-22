package com.bismuth.bismuth.objectDictionary.entry

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.util.*

@Transactional
@RestController
@RequestMapping("project/{projectId}/application/{applicationId}/object-dictionary/{objectDictionaryId}/entry")
class ObjectDictionaryEntryController {

    @Autowired
    lateinit var objectDictionaryEntryService: ObjectDictionaryEntryService;

    @GetMapping
    fun getAllByObjectDictionary(
            @PathVariable("projectId") projectId: UUID,
            @PathVariable("applicationId") applicationId: UUID,
            @PathVariable("objectDictionaryId") objectDictionaryId: UUID
    ): List<ObjectDictionaryEntry> {
        return objectDictionaryEntryService.getAllFromObjectDictionary(projectId, applicationId, objectDictionaryId);
    }

    @GetMapping("/public")
    fun getAllByObjectDictionaryPublicly(
            @PathVariable("projectId") projectId: UUID,
            @PathVariable("applicationId") applicationId: UUID,
            @PathVariable("objectDictionaryId") objectDictionaryId: UUID
    ): List<ObjectDictionaryEntry> {
        return objectDictionaryEntryService.getAllByObjectDictionaryPublicly(projectId, applicationId, objectDictionaryId);
    }

    @DeleteMapping("/{objectDictionaryEntryId}")
    fun deleteObjectDictionaryEntry(
            @PathVariable("projectId") projectId: UUID,
            @PathVariable("applicationId") applicationId: UUID,
            @PathVariable("objectDictionaryId") objectDictionaryId: UUID,
            @PathVariable("objectDictionaryEntryId") objectDictionaryEntryId: UUID
    ) {
        return objectDictionaryEntryService.deleteObjectDictionaryEntry(projectId, applicationId, objectDictionaryId, objectDictionaryEntryId);
    }

    @GetMapping("/search-by-word/{searchWord}")
    fun getAllByObjectDictionaryWithSearchWord(
            @PathVariable("projectId") projectId: UUID,
            @PathVariable("applicationId") applicationId: UUID,
            @PathVariable("objectDictionaryId") objectDictionaryId: UUID,
            @PathVariable("searchWord") searchWord: String
    ): List<ObjectDictionaryEntry> {
        return objectDictionaryEntryService.getAllByObjectDictionaryWithSearchWord(projectId, applicationId, objectDictionaryId, searchWord);
    }

    @PutMapping("/{objectDictionaryEntryId}")
    fun createEntry(
            @PathVariable("projectId") projectId: UUID,
            @PathVariable("applicationId") applicationId: UUID,
            @PathVariable("objectDictionaryId") objectDictionaryId: UUID,
            @PathVariable("objectDictionaryEntryId") objectDictionaryEntryId: UUID,
            @RequestBody objectDictionaryEntry: ObjectDictionaryEntry
    ): ObjectDictionaryEntry {
        return objectDictionaryEntryService.update(objectDictionaryEntryId, objectDictionaryEntry);
    }

    @PostMapping
    fun createEntry(
            @PathVariable("projectId") projectId: UUID,
            @PathVariable("applicationId") applicationId: UUID,
            @PathVariable("objectDictionaryId") objectDictionaryId: UUID,
            @RequestBody objectDictionaryEntry: ObjectDictionaryEntry
    ): ObjectDictionaryEntry {
        return objectDictionaryEntryService.create(objectDictionaryId, objectDictionaryEntry);
    }

}