package com.bismuth.bismuth.httpAPI

import com.bismuth.bismuth.framework.data.OwnableModel
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(
        schema = "public",
        name = "http_api"
)
data class HttpAPI(
        @Id
        @Column(name = "http_api_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var httpApiId: UUID?,
        @Column(name = "name")
        var name: String,
        @Column(name = "application_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var applicationId: UUID?,
        @Column(name = "is_publicly_visible")
        var isPubliclyVisible: Boolean = false
) : OwnableModel();