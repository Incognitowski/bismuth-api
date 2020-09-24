package com.bismuth.bismuth.objectDictionary

import com.bismuth.bismuth.framework.authentication.Auth
import com.bismuth.bismuth.objectDictionary.visibility.ObjectDictionaryVisibility
import com.bismuth.bismuth.objectDictionary.visibility.ObjectDictionaryVisibilityConstants
import com.bismuth.bismuth.objectDictionary.visibility.ObjectDictionaryVisibilityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
@Transactional
class ObjectDictionaryService {

    @Autowired
    lateinit var request: HttpServletRequest;

    @Autowired
    lateinit var objectDictionaryRepository: ObjectDictionaryRepository;

    @Autowired
    lateinit var objectDictionaryVisibilityService: ObjectDictionaryVisibilityService;

    fun getAllVisibleToUser(
            projectId: UUID,
            applicationId: UUID
    ): List<ObjectDictionary> {
        val user = Auth.getAuthenticatedUser(request);
        return objectDictionaryRepository.getAllVisibleToUser(applicationId, user.userId!!);
    }

    fun create(projectId: UUID, applicationId: UUID, objectDictionary: ObjectDictionary): ObjectDictionary {
        val user = Auth.getAuthenticatedUser(request);
        objectDictionary.objectDictionaryId = UUID.randomUUID();
        objectDictionary.applicationId = applicationId;
        objectDictionary.ownedBy = user;
        objectDictionary.createdBy = user;
        ObjectDictionaryBO.validateCreation(objectDictionary);
        val createdObjectDictionary = objectDictionaryRepository.save(objectDictionary);
        objectDictionaryVisibilityService.create(
                ObjectDictionaryVisibility(
                        null,
                        createdObjectDictionary.objectDictionaryId,
                        user.userId!!,
                        ObjectDictionaryVisibilityConstants.OWNER
                )
        )
        return createdObjectDictionary;
    }

}