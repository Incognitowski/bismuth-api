package com.bismuth.bismuth.objectDictionary.visibility

import com.bismuth.bismuth.framework.data.BaseModel
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(
        schema = "public",
        name = "object_dictionary_visibility"
)
data class ObjectDictionaryVisibility(
        @Id
        @Column(name = "object_dictionary_visibility_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var objectDictionaryVisibilityId: UUID?,
        @Column(name = "object_dictionary_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var objectDictionaryId: UUID?,
        @Column(name = "user_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var userId: UUID?,
        @Column(name = "visibility")
        var visibility: String
) : BaseModel();