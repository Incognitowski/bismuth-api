package com.bismuth.bismuth.objectDictionary

import com.bismuth.bismuth.objectDictionary.exception.ObjectDictionaryValidationException

class ObjectDictionaryBO {

    companion object {

        fun validateCreation(objectDictionary: ObjectDictionary) {
            if (objectDictionary.name.length < 5)
                throw ObjectDictionaryValidationException("The name of the object dictionary must have at least 5 characters");
            if (objectDictionary.name.length > 255)
                throw ObjectDictionaryValidationException("The name of the object dictionary can have at most 255 characters");
        }

    }

}