package com.bismuth.bismuth.project.visibility

import com.bismuth.bismuth.framework.data.BaseModel
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(
        name = "project_visibility",
        schema = "public"
)
data class ProjectVisibility(
        @Id
        @Type(type = "pg-uuid")
        @Column(name = "project_visibility_id")
        var project_visibility_id: UUID?,
        @Type(type = "pg-uuid")
        @Column(name = "project_id")
        var project_id: UUID,
        @Type(type = "pg-uuid")
        @Column(name = "user_id")
        var user_id: UUID,
        @Column(name = "visibility")
        var visibility: String
) : BaseModel() {

    fun getVisibilityAsEnum(): ProjectVisibilityEnum {
        return ProjectVisibilityCommons.getVisibilityFrom(this);
    }

};