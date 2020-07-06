package com.bismuth.bismuth.project

class ProjectBO {
    companion object {
        fun validate(project: Project) {
            //todo validate this properly
            validateProjectName(project.name);
        }

        private fun validateProjectName(name: String) {
            if (name.length < 5) {
//                throw InvalidProjectException()
            }
        }
    }
}