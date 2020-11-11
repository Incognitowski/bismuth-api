package com.bismuth.bismuth.httpAPI.request

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface HttpAPIRequestRepository : JpaRepository<HttpAPIRequest, UUID> {

    @Query(value = "SELECT * FROM http_request hr WHERE hr.softdeleted = false AND hr.http_api_id = :httpApiId", nativeQuery = true)
    fun getAllFromHttpAPI(
            @Param("httpApiId") httpApiId: UUID
    ): List<HttpAPIRequest>;

    @Query(value = "SELECT * FROM http_request hr WHERE hr.softdeleted = false AND hr.http_api_id = :httpApiId AND (UPPER(hr.description) LIKE UPPER(:searchWord) OR UPPER(hr.operation_id) LIKE UPPER(:searchWord) OR UPPER(hr.path) LIKE UPPER(:searchWord))", nativeQuery = true)
    fun searchByWordInHttpAPI(
            @Param("httpApiId") httpApiId: UUID,
            @Param("searchWord") searchWord: String
    ): List<HttpAPIRequest>;

};