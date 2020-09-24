package com.bismuth.bismuth.httpAPI

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface HttpAPIRepository : JpaRepository<HttpAPI, UUID> {

    @Query(value = "SELECT ha.* FROM http_api ha WHERE ha.http_api_id IN ( SELECT hav.http_api_id FROM http_api_visibility hav WHERE hav.softdeleted = false AND hav.user_id = :userId GROUP BY hav.http_api_id ) AND ha.softdeleted = false AND ha.application_id = :applicationId", nativeQuery = true)
    fun getAllVisibleToUser(
            @Param("applicationId") applicationId: UUID,
            @Param("userId") userId: UUID
    ): List<HttpAPI>

}