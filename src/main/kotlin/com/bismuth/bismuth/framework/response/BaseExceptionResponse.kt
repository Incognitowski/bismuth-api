package com.bismuth.bismuth.framework.response

data class BaseExceptionResponse(
        var message: String,
        var flags: MutableList<String>
);