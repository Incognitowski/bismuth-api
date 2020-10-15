package com.bismuth.bismuth.objectDictionary.entry

import com.bismuth.bismuth.framework.authentication.Auth
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
    lateinit var request: HttpServletRequest;

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
        // TODO: 14/10/2020  generate event
        return objectDictionaryEntryRepository.save(objectDictionaryEntry);
    }

    fun update(objectDictionaryEntryId: UUID, objectDictionaryEntry: ObjectDictionaryEntry): ObjectDictionaryEntry {
        val user = Auth.getAuthenticatedUser(request);
        // TODO: 14/10/2020 generate event
        return objectDictionaryEntryRepository.save(objectDictionaryEntry);
    }

}