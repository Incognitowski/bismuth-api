package com.bismuth.bismuth.textDocument

import com.bismuth.bismuth.framework.data.OwnableModel
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(
        schema = "public",
        name = "text_document"
)
data class TextDocument(
        @Id
        @Column(name = "text_document_id")
        @org.hibernate.annotations.Type(type = "pg-uuid")
        var textDocumentId: UUID?,
        @Column(name = "application_id")
        var applicationId: UUID,
        @Column(name = "content")
        var content: String
) : OwnableModel();