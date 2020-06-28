package com.bismuth.bismuth.framework.interceptor

import com.bismuth.bismuth.framework.authentication.Auth
import com.bismuth.bismuth.framework.authentication.JwtUtils
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
                    // to return the project structure if it is public
                    "/project/{projectId}",
                    // to return the project applications if the project is public
                    "/project/{projectId}/application",
                    "/project/{projectId}/application/{applicationId}",
                    // to return the application if it is public
                    "/project/{projectId}/application/{applicationId}"
            );
        }
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val jwt: String = request.getHeader("Authorization");
        val user: User = userService.getByDecodedJWT(JwtUtils.retrieveTokenInformation(jwt));
        Auth.injectAuthenticatedUser(user, request);
        return true;
    }

}