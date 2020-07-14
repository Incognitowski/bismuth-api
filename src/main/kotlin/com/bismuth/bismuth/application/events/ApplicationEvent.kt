package com.bismuth.bismuth.application.events

import com.bismuth.bismuth.framework.data.BaseModel
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(
        name = "application_event",
        schema = "public"
)
data class ApplicationEvent(
        @Id
        @Column(name = "application_event_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var applicationEventId: UUID?,
        @Column(name = "application_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var applicationId: UUID,
        @Column(name = "user_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var userId: UUID?,
        @Column(name = "event")
        var event: String
) : BaseModel();