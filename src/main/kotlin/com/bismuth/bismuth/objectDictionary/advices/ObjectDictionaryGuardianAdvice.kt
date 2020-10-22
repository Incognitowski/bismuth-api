package com.bismuth.bismuth.objectDictionary.advices

import com.bismuth.bismuth.framework.response.BaseExceptionResponse
import com.bismuth.bismuth.objectDictionary.exceptions.ObjectDictionaryEntryValidationException
import com.bismuth.bismuth.objectDictionary.exceptions.ObjectDictionaryGuardianException
import com.bismuth.bismuth.objectDictionary.exceptions.ObjectDictionaryValidationException
import com.bismuth.bismuth.project.exceptions.ProjectValidationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ObjectDictionaryGuardianAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ObjectDictionaryGuardianException::class)
    fun handleSecurityException(exception: ObjectDictionaryGuardianException): BaseExceptionResponse {
        return BaseExceptionResponse(
                exception.message ?: "An unexpected error occurred. Sorry for that :/",
                mutableListOf()
        );
    }

}