package com.bismuth.bismuth.textDocument

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TextDocumentRepository : JpaRepository<TextDocument, UUID> {

    fun getAllByApplicationId(applicationId : UUID) : List<TextDocument>;

};