package com.bismuth.bismuth.objectDictionary.entry

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ObjectDictionaryEntryRepository : JpaRepository<ObjectDictionaryEntry, UUID> {

    @Query(value = "SELECT * FROM object_dictionary_entry WHERE object_dictionary_id = :objectDictionaryId", nativeQuery = true)
    fun getAllByObjectDictionary(
            @Param("objectDictionaryId") objectDictionaryId: UUID
    ): List<ObjectDictionaryEntry>

}