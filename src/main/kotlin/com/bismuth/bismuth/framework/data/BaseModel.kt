package com.bismuth.bismuth.framework.data

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.util.*
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseModel : Serializable {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    lateinit var createdAt: Date;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    lateinit var updatedAt: Date;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "softdeleted_at", nullable = true)
    var softdeletedAt: Date? = null;

    @Column(name = "softdeleted", nullable = false)
    var isSoftdeleted: Boolean = false;

}