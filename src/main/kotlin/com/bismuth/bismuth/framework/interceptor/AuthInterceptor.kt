package com.bismuth.bismuth.framework.interceptor

import com.bismuth.bismuth.framework.authentication.Auth
import com.bismuth.bismuth.framework.authentication.JwtUtils
import com.bismuth.bismuth.framework.exception.AuthenticationRequiredException
import com.bismuth.bismuth.user.User
import com.bismuth.bismuth.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthInterceptor : HandlerInterceptor {

    @Autowired
    lateinit var userService: UserService;

    companion object {
        fun excludePaths(): List<String> {
            return mutableListOf(
                    // so the user can create an account
                    "/user",
                    // so the user can authenticate
                    "/login",
                    // so the user can validate his jwt
                    "/jwt"
            );
        }
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (request.method == "OPTIONS") return true;
        if (request.getHeader(  "Authorization") == null)
            throw AuthenticationRequiredException("You must be authenticated in order to consume this resource.");
        val jwt: String = request.getHeader("Authorization");
        val user: User = userService.getByDecodedJWT(JwtUtils.retrieveTokenInformation(jwt));
        Auth.injectAuthenticatedUser(user, request);
        return true;
    }

}