package com.bismuth.bismuth.framework.advices

import com.auth0.jwt.exceptions.SignatureVerificationException
import com.bismuth.bismuth.framework.exception.EntityNotFoundException
import com.bismuth.bismuth.framework.response.BaseExceptionResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class SignatureVerificationAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(SignatureVerificationException::class)
    fun handleSecurityException(exception: SignatureVerificationException): BaseExceptionResponse {
        return BaseExceptionResponse(
                "The JWT's Signature resulted invalid when verified",
                mutableListOf("redirect_to_login")
        );
    }

}