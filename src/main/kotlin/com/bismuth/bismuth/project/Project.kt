package com.bismuth.bismuth.project

import com.bismuth.bismuth.framework.data.OwnableModel
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(
        name = "project",
        schema = "public"
)
data class Project(
        @Id
        @Column(name = "project_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var project_id: UUID?,
        @Column(name = "name")
        var name: String,
        @Column(name = "active")
        var active: Boolean,
        @Column(name = "is_publicly_visible")
        var isPubliclyVisible: Boolean
) : OwnableModel();