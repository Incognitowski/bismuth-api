package com.bismuth.bismuth.user

import com.bismuth.bismuth.user.exceptions.UserValidationException

class UserBO {
    companion object {

        fun validate(user: User) {
            validateUsername(user.username);
            validatePassword(user.password);
        }

        private fun validateUsername(username: String?) {
            if (username.isNullOrBlank())
                throw UserValidationException("Your username cannot be empty. C'mon dude...");
            if (username.length < 5)
                throw UserValidationException("Your username must be at least 5 characters long.");
            if (username.contains(" "))
                throw UserValidationException("Your username cannot contain blank characters.");
        }

        private fun validatePassword(password: String) {
            if (password.length < 8)
                throw UserValidationException("Your password must be at least 8 characters.")
            if (!password.contains("\\d".toRegex()))
                throw UserValidationException("Your password must contain at least one number.")
            if (!password.contains("[A-Z]".toRegex()))
                throw UserValidationException("Your password must contain at least one upper case character.");
            if (!password.contains("[a-z]".toRegex()))
                throw UserValidationException("Your password must contain at least one upper case character.");
        }

    }
}