package com.bismuth.bismuth.project.events

import com.bismuth.bismuth.framework.data.BaseModel
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(
        name = "project_event",
        schema = "public"
)
data class ProjectEvent(
        @Id
        @Column(name = "project_event_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var project_event_id: UUID?,
        @Column(name = "event")
        var event: String,
        @Column(name = "project_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var project_id: UUID,
        @Column(name = "user_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var user_id: UUID?
) : BaseModel();