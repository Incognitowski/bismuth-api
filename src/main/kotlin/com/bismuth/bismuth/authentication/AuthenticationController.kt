package com.bismuth.bismuth.authentication

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
class AuthenticationController {

    @Autowired
    lateinit var authenticationService: AuthenticationService;

    @PostMapping("login")
    fun attemptAuthentication(@RequestBody authenticationPOKO: AuthenticationPOKO): AuthenticationSuccessfulPOKO {
        return authenticationService.attemptAuthentication(authenticationPOKO);
    }

    @GetMapping("jwt")
    fun checkJWTValidity(request: HttpServletRequest) {
        return authenticationService.checkIfJWTIsValid(request);
    }

}