package com.bismuth.bismuth.user

import com.bismuth.bismuth.framework.data.BaseModel
import com.bismuth.bismuth.project.Project
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user", schema = "public")
@JsonIgnoreProperties(
        value = ["password"],
        allowSetters = true
)
data class User(
        @Id
        @Column(name = "user_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var userId: UUID?,
        @Column(name = "username")
        var username: String?,
        @Column(name = "email")
        var email: String,
        @JsonIgnore
        @Column(name = "password")
        var password: String?,
        @Column(name = "suspended")
        var suspended: Boolean = false
) : BaseModel();