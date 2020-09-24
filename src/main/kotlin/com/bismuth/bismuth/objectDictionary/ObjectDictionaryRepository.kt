package com.bismuth.bismuth.objectDictionary

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ObjectDictionaryRepository : JpaRepository<ObjectDictionary, UUID> {


    @Query(value = "", nativeQuery = true)
    fun getAllVisibleToUser(
            @Param("applicationId") applicationId: UUID,
            @Param("userId") userId: UUID
    ): List<ObjectDictionary>;

}