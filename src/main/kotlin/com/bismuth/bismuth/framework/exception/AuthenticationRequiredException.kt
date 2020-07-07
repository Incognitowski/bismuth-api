package com.bismuth.bismuth.framework.exception

import java.lang.Exception

class AuthenticationRequiredException(errorMessage: String) : Exception(errorMessage)