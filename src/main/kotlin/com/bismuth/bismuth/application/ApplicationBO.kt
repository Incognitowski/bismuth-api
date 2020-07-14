package com.bismuth.bismuth.application

import com.bismuth.bismuth.application.exceptions.ApplicationValidationException

class ApplicationBO {
    companion object {

        fun validate(application: Application) {
            validateApplicationName(application.name);
        }

        private fun validateApplicationName(name: String) {
            if (name.length < 5)
                throw ApplicationValidationException("The name of the application should be at least 5 characters long.")
        }

    }
}