package com.bismuth.bismuth.framework.authentication

import com.bismuth.bismuth.user.User
import javax.servlet.http.HttpServletRequest

class Auth {
    companion object {
        private const val USER_ATTRIBUTE_KEY: String = "authenticated_user";

        fun injectAuthenticatedUser(user: User, request: HttpServletRequest) {
            request.setAttribute(USER_ATTRIBUTE_KEY, user);
        }

        fun getAuthenticatedUser(request: HttpServletRequest): User {
            return request.getAttribute(USER_ATTRIBUTE_KEY) as User;
        }
    }

}