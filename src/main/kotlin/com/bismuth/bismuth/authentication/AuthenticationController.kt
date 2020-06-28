package com.bismuth.bismuth.authentication

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationController {

    @Autowired
    lateinit var authenticationService: AuthenticationService;

    @PostMapping("login")
    fun attemptAuthentication(@RequestBody authenticationPOKO: AuthenticationPOKO): AuthenticationSuccessfulPOKO {
        return authenticationService.attemptAuthentication(authenticationPOKO);
    }

}