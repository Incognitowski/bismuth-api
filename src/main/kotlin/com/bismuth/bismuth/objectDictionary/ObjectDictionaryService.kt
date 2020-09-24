package com.bismuth.bismuth.objectDictionary

import com.bismuth.bismuth.framework.authentication.Auth
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
class ObjectDictionaryService {

    @Autowired
    lateinit var request: HttpServletRequest;

    @Autowired
    lateinit var objectDictionaryRepository: ObjectDictionaryRepository;

    fun getAllVisibleToUser(
            projectId: UUID,
            applicationId: UUID
    ): List<ObjectDictionary> {
        val user = Auth.getAuthenticatedUser(request);
        return objectDictionaryRepository.getAllVisibleToUser(applicationId, user.userId!!);
    }

    fun create(projectId: UUID, applicationId: UUID, objectDictionary: ObjectDictionary): ObjectDictionary {
        objectDictionary.objectDictionaryId = UUID.randomUUID();
        objectDictionary.applicationId = applicationId;

        return objectDictionaryRepository.save(objectDictionary);
    }

}