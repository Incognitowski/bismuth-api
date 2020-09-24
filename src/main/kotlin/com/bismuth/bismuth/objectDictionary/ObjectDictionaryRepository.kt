package com.bismuth.bismuth.objectDictionary

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ObjectDictionaryRepository : JpaRepository<ObjectDictionary, UUID> {

    @Query(value = "SELECT od.* FROM object_dictionary od WHERE od.object_dictionary_id IN (SELECT odv.object_dictionary_id FROM object_dictionary_visibility odv WHERE odv.softdeleted = false AND odv.user_id = :userId GROUP BY odv.object_dictionary_id ) AND od.softdeleted = false AND od.application_id = :applicationId", nativeQuery = true)
    fun getAllVisibleToUser(
            @Param("applicationId") applicationId: UUID,
            @Param("userId") userId: UUID
    ): List<ObjectDictionary>;

}