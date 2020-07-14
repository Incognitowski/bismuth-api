package com.bismuth.bismuth.application.visibility

import com.bismuth.bismuth.framework.data.BaseModel
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(
        name = "application_visibility",
        schema = "public"
)
data class ApplicationVisibility(
        @Id
        @Column(name = "application_visibility_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var applicationVisibilityId: UUID?,
        @Column(name = "application_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var applicationId: UUID,
        @Column(name = "user_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var userId: UUID,
        @Column(name = "visibility")
        var visibility: String
) : BaseModel()