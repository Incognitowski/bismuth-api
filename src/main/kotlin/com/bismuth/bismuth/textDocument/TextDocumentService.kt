package com.bismuth.bismuth.textDocument

import com.bismuth.bismuth.framework.authentication.Auth
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
@Transactional
class TextDocumentService {

    @Autowired
    lateinit var request: HttpServletRequest;

    @Autowired
    lateinit var textDocumentRepository: TextDocumentRepository;

    fun create(applicationId: UUID, textDocument: TextDocument): TextDocument {
        val user = Auth.getAuthenticatedUser(request);
        textDocument.applicationId = applicationId;
        textDocument.textDocumentId = UUID.randomUUID();
        textDocument.createdBy = user;
        textDocument.ownedBy = user;
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