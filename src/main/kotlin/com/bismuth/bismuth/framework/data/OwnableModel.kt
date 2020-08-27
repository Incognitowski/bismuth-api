package com.bismuth.bismuth.framework.data

import com.bismuth.bismuth.user.User
import java.util.*
import javax.persistence.Column
import javax.persistence.JoinColumn
import javax.persistence.MappedSuperclass
import javax.persistence.OneToOne

@MappedSuperclass
open class OwnableModel : BaseModel() {

    @OneToOne
    @JoinColumn(name = "created_by")
    @org.hibernate.annotations.Type(type = "pg-uuid")
    var createdBy: User? = null;

    @OneToOne
    @JoinColumn(name = "owned_by")
    @org.hibernate.annotations.Type(type = "pg-uuid")
    var ownedBy: User? = null;
}