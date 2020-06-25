package com.bismuth.bismuth.user

import com.bismuth.bismuth.framework.data.BaseModel
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user", schema = "public")
data class User(
        @Id
        @Column(name = "user_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var user_id: UUID?,
        @Column(name = "username")
        var username: String,
        @Column(name = "email")
        var email: String,
        @Column(name = "password")
        var password: String
) : BaseModel();