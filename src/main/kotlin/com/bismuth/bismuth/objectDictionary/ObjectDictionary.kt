package com.bismuth.bismuth.objectDictionary

import com.bismuth.bismuth.framework.data.OwnableModel
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class ObjectDictionary(
        @Id
        @Column(name = "object_dictionary_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var objectDictionaryId: UUID?,
        @Column(name = "name")
        var name: String,
        @Column(name = "application_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var applicationId: UUID?,
        @Column(name = "is_publicly_visible")
        var isPubliclyVisible: Boolean = false
) : OwnableModel();