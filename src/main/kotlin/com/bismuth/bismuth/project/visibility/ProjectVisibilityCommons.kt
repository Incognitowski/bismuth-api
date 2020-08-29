package com.bismuth.bismuth.project.visibility

class ProjectVisibilityCommons {
    companion object {
        fun getVisibilityFrom(projectVisibility: ProjectVisibility): ProjectVisibilityEnum {
            when (projectVisibility.visibility) {
                ProjectVisibilityConstants.OWNER -> return ProjectVisibilityEnum.OWNER;
                ProjectVisibilityConstants.MANAGER -> return ProjectVisibilityEnum.MANAGER;
                ProjectVisibilityConstants.DEVELOPER -> return ProjectVisibilityEnum.DEVELOPER;
                ProjectVisibilityConstants.STAKEHOLDER -> return ProjectVisibilityEnum.STAKEHOLDER;
            }
            return ProjectVisibilityEnum.STAKEHOLDER;
        }
    }
}