package com.bismuth.bismuth.objectDictionary.entry

import com.bismuth.bismuth.framework.authentication.Auth
import com.bismuth.bismuth.objectDictionary.ObjectDictionaryGuardian
import com.bismuth.bismuth.objectDictionary.ObjectDictionaryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
@Transactional
class ObjectDictionaryEntryService {

    @Autowired
    lateinit var objectDictionaryEntryRepository: ObjectDictionaryEntryRepository;

    @Autowired
    lateinit var objectDictionaryService : ObjectDictionaryService;

    @Autowired
    lateinit var request: HttpServletRequest;

    fun getAllByObjectDictionaryPublicly(projectId: UUID, applicationId: UUID, objectDictionaryId: UUID): List<ObjectDictionaryEntry> {
        val objectDictionary = objectDictionaryService.getObjectDictionary(projectId, applicationId, objectDictionaryId);
        ObjectDictionaryGuardian.protectPublicAccessOf(objectDictionary);
        return objectDictionaryEntryRepository.getAllByObjectDictionary(objectDictionaryId);
    }

    fun getAllFromObjectDictionary(
            projectId: UUID,
            applicationId: UUID,
            objectDictionaryId: UUID
    ): List<ObjectDictionaryEntry> {
        return objectDictionaryEntryRepository.getAllByObjectDictionary(objectDictionaryId);
    }

    fun create(
            objectDictionaryId: UUID,
            objectDictionaryEntry: ObjectDictionaryEntry
    ): ObjectDictionaryEntry {
        val user = Auth.getAuthenticatedUser(request);
        objectDictionaryEntry.objectDictionaryEntryId = UUID.randomUUID();
        objectDictionaryEntry.ownedBy = user;
        objectDictionaryEntry.createdBy = user;
        ObjectDictionaryEntryBO.validate(objectDictionaryEntry);
        // TODO: 14/10/2020  generate event
        return objectDictionaryEntryRepository.save(objectDictionaryEntry);
    }

    fun update(objectDictionaryEntryId: UUID, objectDictionaryEntry: ObjectDictionaryEntry): ObjectDictionaryEntry {
        // TODO: 14/10/2020 generate event
        ObjectDictionaryEntryBO.validate(objectDictionaryEntry);
        return objectDictionaryEntryRepository.save(objectDictionaryEntry);
    }

    fun getAllByObjectDictionaryWithSearchWord(
            projectId: UUID,
            applicationId: UUID,
            objectDictionaryId: UUID,
            searchWord: String
    ): List<ObjectDictionaryEntry> {
        return objectDictionaryEntryRepository.getAllByObjectDictionaryWithSearchWord(
                objectDictionaryId,
                "%${searchWord}%"
        );
    }

    fun deleteObjectDictionaryEntry(
            projectId: UUID,
            applicationId: UUID,
            objectDictionaryId: UUID,
            objectDictionaryEntryId: UUID
    ) {
        val objectDictionaryEntryToDeleteSearch = objectDictionaryEntryRepository.findById(objectDictionaryEntryId);
        if (!objectDictionaryEntryToDeleteSearch.isPresent) return;
        val objectDictionaryEntry = objectDictionaryEntryToDeleteSearch.get();
        objectDictionaryEntry.isSoftdeleted = true;
        objectDictionaryEntry.softdeletedAt = Date();
        objectDictionaryEntryRepository.save(objectDictionaryEntry);
    }

}