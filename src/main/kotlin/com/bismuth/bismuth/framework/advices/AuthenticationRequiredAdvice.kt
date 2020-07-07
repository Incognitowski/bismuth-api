package com.bismuth.bismuth.framework.advices

import com.bismuth.bismuth.framework.exception.AuthenticationRequiredException
import com.bismuth.bismuth.framework.response.BaseExceptionResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class AuthenticationRequiredAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthenticationRequiredException::class)
    fun handleSecurityException(exception: AuthenticationRequiredException): BaseExceptionResponse {
        return BaseExceptionResponse(
                exception.message ?: "An unexpected error occurred. Sorry for that :/",
                mutableListOf()
        );
    }

}