package com.bismuth.bismuth.project

import com.bismuth.bismuth.framework.data.OwnableModel
import com.bismuth.bismuth.user.User
import java.util.*
import javax.persistence.*


@Entity
@Table(
        name = "project",
        schema = "public"
)
data class Project(
        @Id
        @Column(name = "project_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var projectId: UUID?,
        @Column(name = "name")
        var name: String,
        @Column(name = "active")
        var active: Boolean,
        @Column(name = "is_publicly_visible")
        var isPubliclyVisible: Boolean
) : OwnableModel();