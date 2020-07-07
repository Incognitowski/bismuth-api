package com.bismuth.bismuth.project.visibility

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProjectVisibilityRepository : JpaRepository<ProjectVisibility, UUID> {

    @Query(value = "SELECT * FROM project_visibility WHERE project_id = :projectId AND user_id = :userId AND softdeleted = false LIMIT 1", nativeQuery = true)
    fun getVisibilityOf(
            @Param("userId") userId: UUID,
            @Param("projectId") projectId: UUID
    ): ProjectVisibility?

};