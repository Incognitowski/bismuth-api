package com.bismuth.bismuth.project.advices

import com.bismuth.bismuth.framework.response.BaseExceptionResponse
import com.bismuth.bismuth.project.exceptions.ProjectValidationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ProjectValidationAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ProjectValidationException::class)
    fun handleSecurityException(exception: ProjectValidationException): BaseExceptionResponse {
        return BaseExceptionResponse(
                exception.message ?: "An unexpected error occurred. Sorry for that :/",
                mutableListOf()
        );
    }

}