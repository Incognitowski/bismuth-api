package com.bismuth.bismuth.application

import com.bismuth.bismuth.application.visibility.ApplicationVisibility
import com.bismuth.bismuth.framework.data.OwnableModel
import com.bismuth.bismuth.project.Project
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(
        name = "application",
        schema = "public"
)
data class Application(
        @Id
        @Column(name = "application_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var applicationId: UUID?,
        @Column(name = "name")
        var name: String,
        @Column(name = "project_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var projectId: UUID?,
        @Column(name = "is_publicly_visible")
        var isPubliclyVisible: Boolean = false
) : OwnableModel() {

    @Transient
    var relationShipWithCurrentUser: ApplicationVisibility? = null;

    @Transient
    var project: Project? = null;

};