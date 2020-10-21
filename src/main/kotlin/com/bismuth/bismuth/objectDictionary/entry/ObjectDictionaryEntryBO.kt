package com.bismuth.bismuth.objectDictionary.entry

import com.bismuth.bismuth.objectDictionary.exceptions.ObjectDictionaryEntryValidationException

class ObjectDictionaryEntryBO {

    companion object {

        fun validate(objectDictionaryEntry: ObjectDictionaryEntry) {
            if (objectDictionaryEntry.name.isNullOrBlank())
                throw ObjectDictionaryEntryValidationException("You must provide a name for your entry.")
            if (objectDictionaryEntry.description.isNullOrBlank())
                throw ObjectDictionaryEntryValidationException("You must provide a description for your entry.")
        }

    }

}