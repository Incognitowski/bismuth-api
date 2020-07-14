package com.bismuth.bismuth.application.visibility

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ApplicationVisibilityRepository : JpaRepository<ApplicationVisibility, UUID> {

    @Query(value = "SELECT * FROM application_visibility WHERE application_id = :applicationId AND user_id = :userId AND softdeleted = false LIMIT 1", nativeQuery = true)
    fun getVisibilityOf(
            @Param("userId") userId: UUID,
            @Param("applicationId") applicationId: UUID
    ): ApplicationVisibility?

}