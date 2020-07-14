package com.bismuth.bismuth.application

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ApplicationRepository : JpaRepository<Application, UUID> {

    @Query(value = "SELECT a.* FROM application a JOIN application_visibility av ON av.application_id = a.application_id WHERE a.softdeleted = false AND av.softdeleted = false AND a.project_id = :projectId AND av.user_id = :userId", nativeQuery = true)
    fun getByProjectId(
            @Param("projectId") projectId: UUID,
            @Param("userId") userId: UUID
    ): List<Application>;

}