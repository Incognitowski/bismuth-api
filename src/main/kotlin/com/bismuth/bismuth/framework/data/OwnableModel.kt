package com.bismuth.bismuth.framework.data

import java.util.*
import javax.persistence.Column
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class OwnableModel : BaseModel() {
    @Column(name = "created_by")
    @org.hibernate.annotations.Type(type = "pg-uuid")
    public var createdBy: UUID? = null;

    @Column(name = "owned_by")
    @org.hibernate.annotations.Type(type = "pg-uuid")
    public var ownedBy: UUID? = null;
}