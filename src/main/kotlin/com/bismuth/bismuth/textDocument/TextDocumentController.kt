package com.bismuth.bismuth.textDocument

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.util.*

@Transactional
@RestController
@RequestMapping("project/{projectId}/application/{applicationId}/text-document")
class TextDocumentController {

    @Autowired
    lateinit var textDocumentService: TextDocumentService;

    @GetMapping
    fun getAllByHttpAPI(
            @PathVariable("projectId") projectId: UUID,
            @PathVariable("applicationId") applicationId: UUID
    ): List<TextDocument> = textDocumentService.getByAppId(applicationId);

    @GetMapping("/{textDocumentId}")
    fun searchRequestsByWord(
            @PathVariable("projectId") projectId: UUID,
            @PathVariable("applicationId") applicationId: UUID,
            @PathVariable("textDocumentId") textDocumentId: UUID
    ): TextDocument = textDocumentService.getById(textDocumentId);

    @PostMapping
    fun create(
            @PathVariable("projectId") projectId: UUID,
            @PathVariable("applicationId") applicationId: UUID,
            @RequestBody textDocument: TextDocument
    ): TextDocument = textDocumentService.create(applicationId, textDocument);

    @PutMapping("/{textDocumentId}")
    fun update(
            @PathVariable("projectId") projectId: UUID,
            @PathVariable("applicationId") applicationId: UUID,
            @PathVariable("textDocumentId") textDocumentId: UUID,
            @RequestBody textDocument: TextDocument
    ): TextDocument = textDocumentService.update(textDocument);

    @DeleteMapping("/{textDocumentId}")
    fun delete(
            @PathVariable("projectId") projectId: UUID,
            @PathVariable("applicationId") applicationId: UUID,
            @PathVariable("textDocumentId") textDocumentId: UUID
    ) = textDocumentService.delete(textDocumentId);

}