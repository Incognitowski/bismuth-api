package com.bismuth.bismuth.project.events

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProjectEventRepository : JpaRepository<ProjectEvent, UUID> {

    @Query(
            value = "SELECT * FROM project_event WHERE project_id = :projectId AND softdeleted = false ORDER BY created_at DESC;",
            nativeQuery = true
    )
    fun getAllFromProject(
            @Param(value = "projectId") projectId: UUID
    ): List<ProjectEvent>;

};
