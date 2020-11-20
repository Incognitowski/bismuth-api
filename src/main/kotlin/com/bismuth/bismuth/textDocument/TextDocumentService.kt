package com.bismuth.bismuth.textDocument

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class TextDocumentService {

    @Autowired
    lateinit var textDocumentRepository: TextDocumentRepository;

    fun create(applicationId: UUID, textDocument: TextDocument): TextDocument {
        textDocument.applicationId = applicationId;
        textDocument.textDocumentId = UUID.randomUUID();
        return textDocumentRepository.save(textDocument);
    };

    fun update(textDocument: TextDocument): TextDocument {
        return textDocumentRepository.save(textDocument);
    }

    fun delete(textDocumentId: UUID) {
        textDocumentRepository.deleteById(textDocumentId);
    }

    fun getById(textDocumentId: UUID): TextDocument {
        return textDocumentRepository.findById(textDocumentId).get();
    }

    fun getByAppId(applicationId: UUID): List<TextDocument> {
        return textDocumentRepository.getAllByApplicationId(applicationId);
    }
}