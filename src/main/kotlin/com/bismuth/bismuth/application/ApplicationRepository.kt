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

    @Query(value = "SELECT a.* FROM application a WHERE a.application_id IN (SELECT av.application_id FROM application_visibility av WHERE av.user_id = :userId AND av.softdeleted = false GROUP BY av.application_id ) AND a.project_id = :projectId AND a.softdeleted = false", nativeQuery = true)
    fun getAppsVisibleToUser(
            @Param("userId") userId: UUID,
            @Param("projectId") projectId: UUID
    ): List<Application>

    @Query(value = "select a.* from application a where a.application_id in ( select av.application_id from application_visibility av where av.user_id = :userId AND av.softdeleted = false GROUP BY av.application_id ) AND a.project_id = :projectId AND UPPER(a.name) LIKE UPPER(:searchWord) AND a.softdeleted = false", nativeQuery = true)
    fun searchByName(
            @Param("projectId") projectId: UUID,
            @Param("searchWord") searchWord: String,
            @Param("userId") userId: UUID
    ): List<Application>

}