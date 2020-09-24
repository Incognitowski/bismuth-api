package com.bismuth.bismuth.httpAPI.visibility

import com.bismuth.bismuth.framework.data.BaseModel
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(
        schema = "public",
        name = "http_api_visibility"
)
data class HttpAPIVisibility(
        @Id
        @Column(name = "http_api_visibility_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var httpApiVisibilityId: UUID?,
        @Column(name = "http_api_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var httpAPIId: UUID?,
        @Column(name = "user_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var userId: UUID?,
        @Column(name = "visibility")
        var visibility: String
) : BaseModel();