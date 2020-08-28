package com.bismuth.bismuth.project

import com.bismuth.bismuth.framework.data.OwnableModel
import com.bismuth.bismuth.project.visibility.ProjectVisibility
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Transient


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
) : OwnableModel() {

        @Transient
        var relationShipWithCurrentUser: ProjectVisibility? = null;

};