package com.bismuth.bismuth.objectDictionary.visibility

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ObjectDictionaryVisibilityService {

    @Autowired
    lateinit var objectDictionaryVisibilityRepository: ObjectDictionaryVisibilityRepository;

    fun create(objectDictionaryVisibility: ObjectDictionaryVisibility): ObjectDictionaryVisibility {
        objectDictionaryVisibility.objectDictionaryVisibilityId = UUID.randomUUID();
        return objectDictionaryVisibilityRepository.save(objectDictionaryVisibility);
    }

}