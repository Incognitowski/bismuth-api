package com.bismuth.bismuth.project.advices

import com.bismuth.bismuth.framework.response.BaseExceptionResponse
import com.bismuth.bismuth.project.exceptions.ProjectGuardianException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ProjectGuardianAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ProjectGuardianException::class)
    fun handleSecurityException(exception: ProjectGuardianException): BaseExceptionResponse {
        return BaseExceptionResponse(
                exception.message ?: "An unexpected error occurred. Sorry for that :/",
                mutableListOf()
        );
    }

}