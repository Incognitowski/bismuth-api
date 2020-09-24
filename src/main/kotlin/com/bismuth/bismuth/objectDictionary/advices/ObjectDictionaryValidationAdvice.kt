package com.bismuth.bismuth.objectDictionary.advices

import com.bismuth.bismuth.framework.response.BaseExceptionResponse
import com.bismuth.bismuth.objectDictionary.exceptions.ObjectDictionaryValidationException
import com.bismuth.bismuth.project.exceptions.ProjectValidationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ObjectDictionaryValidationAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ObjectDictionaryValidationException::class)
    fun handleSecurityException(exception: ObjectDictionaryValidationException): BaseExceptionResponse {
        return BaseExceptionResponse(
                exception.message ?: "An unexpected error occurred. Sorry for that :/",
                mutableListOf()
        );
    }

}