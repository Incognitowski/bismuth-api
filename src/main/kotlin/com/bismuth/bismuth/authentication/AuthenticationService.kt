package com.bismuth.bismuth.authentication

import com.bismuth.bismuth.framework.authentication.JwtUtils
import com.bismuth.bismuth.framework.exception.InvalidUsernamePasswordException
import com.bismuth.bismuth.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService {

    @Autowired
    lateinit var userService: UserService;

    fun attemptAuthentication(authenticationPOKO: AuthenticationPOKO): AuthenticationSuccessfulPOKO {
        val user = userService.getByUsername(authenticationPOKO.username);
        if (!BCryptPasswordEncoder().matches(authenticationPOKO.password, user.password))
            throw InvalidUsernamePasswordException("Incorrect username or password");
        return AuthenticationSuccessfulPOKO(JwtUtils.createJWT(user));
    }

}
