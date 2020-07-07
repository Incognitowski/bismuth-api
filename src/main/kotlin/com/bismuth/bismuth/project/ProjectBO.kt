package com.bismuth.bismuth.project

import com.bismuth.bismuth.project.exceptions.ProjectValidationException

class ProjectBO {
    companion object {
        fun validate(project: Project) {
            validateProjectName(project.name);
        }

        private fun validateProjectName(name: String) {
            if (name.length < 5) {
                throw ProjectValidationException("The project's name should be at least 5 characters long.")
            }
        }
    }
}