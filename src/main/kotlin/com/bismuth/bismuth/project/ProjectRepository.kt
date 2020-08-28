package com.bismuth.bismuth.project

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProjectRepository : JpaRepository<Project, UUID> {

    @Query(
            value = "SELECT * FROM project p WHERE project_id in (select project_id from project_visibility pv where pv.user_id = :userId and pv.softdeleted = false) AND p.softdeleted = false",
            nativeQuery = true
    )
    fun getByUserId(
            @Param(value = "userId") userId: UUID
    ): List<Project>;

    @Query(
            value = "SELECT * FROM project p WHERE project_id in (select project_id from project_visibility pv where pv.user_id = :userId and pv.softdeleted = false) AND p.softdeleted = false AND UPPER(p.name) LIKE UPPER(:searchQuery);",
            nativeQuery = true
    )
    fun getByDescription(
            @Param(value = "userId") userId: UUID,
            @Param(value = "searchQuery") searchQuery: String
    ): List<Project>;

}