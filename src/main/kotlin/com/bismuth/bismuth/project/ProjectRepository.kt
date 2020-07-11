package com.bismuth.bismuth.project

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProjectRepository : JpaRepository<Project, UUID> {

    @Query(
            value = "SELECT p.* FROM project p JOIN project_visibility pv on pv.user_id = :userId WHERE pv.softdeleted = false AND p.softdeleted = false GROUP BY p.project_id OFFSET :skip LIMIT :take",
            nativeQuery = true
    )
    fun getByUserId(
            @Param(value = "userId") userId: UUID,
            @Param(value = "skip") skip: Int,
            @Param(value = "take") take: Int
    ): List<Project>;

    @Query(
            value = "SELECT p.* FROM project p JOIN project_visibility pv on pv.user_id = :userId WHERE pv.softdeleted = false AND p.softdeleted = false GROUP BY p.project_id",
            nativeQuery = true
    )
    fun getByUserId(
            @Param(value = "userId") userId: UUID
    ): List<Project>;

    @Query(
            value = "SELECT p.* FROM project p JOIN project_visibility pv on pv . user_id = :userId WHERE pv . softdeleted = false AND p.softdeleted = false AND UPPER(p.name) LIKE UPPER(:searchQuery) GROUP BY p . project_id",
            nativeQuery = true
    )
    fun getByDescription(
            @Param(value = "userId") userId: UUID,
            @Param(value = "searchQuery") searchQuery: String
    ): List<Project>;

}