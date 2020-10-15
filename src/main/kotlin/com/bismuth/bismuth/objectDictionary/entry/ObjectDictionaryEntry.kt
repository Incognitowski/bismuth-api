package com.bismuth.bismuth.objectDictionary.entry

import com.bismuth.bismuth.framework.data.OwnableModel
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(
        schema = "public",
        name = "object_dictionary_entry"
)
data class ObjectDictionaryEntry(
        @Id
        @Type(type = "pg-uuid")
        @Column(name = "object_dictionary_entry_id")
        var objectDictionaryEntryId: UUID?,
        @Column(name = "name")
        var name: String,
        @Type(type = "pg-uuid")
        @Column(name = "object_dictionary_id")
        val objectDictionaryId : UUID?,
        @Column(name = "structure")
        val structure: String?
) : OwnableModel();