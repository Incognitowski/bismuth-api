package com.bismuth.bismuth.httpAPI.request

import com.bismuth.bismuth.framework.data.OwnableModel
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(
        schema = "public",
        name = "http_request"
)
data class HttpAPIRequest(
        @Id
        @Column(name = "http_request_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var httpRequestId: UUID?,
        @Column(name = "operation_id")
        var operationId: String,
        @Column(name = "path")
        var path: String,
        @Column(name = "method")
        var method: String,
        @Column(name = "description")
        var description: String,
        @Column(name = "parameters")
        var parameters: String,
        @Column(name = "request_bodies")
        var request_bodies: String,
        @Column(name = "responses")
        var responses: String,
        @Column(name = "http_api_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var httpApiId: UUID?,
        @Column(name = "deprecated")
        var deprecated: Boolean,
        @Column(name = "deprecated_for")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var deprecatedFor: UUID?,
        @Column(name = "draft")
        var draft: Boolean
) : OwnableModel();