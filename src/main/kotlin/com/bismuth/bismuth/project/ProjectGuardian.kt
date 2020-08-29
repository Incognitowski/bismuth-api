package com.bismuth.bismuth.project

import com.bismuth.bismuth.framework.Cthulhu
import com.bismuth.bismuth.project.exceptions.ProjectGuardianException
import com.bismuth.bismuth.project.visibility.ProjectVisibility
import com.bismuth.bismuth.project.visibility.ProjectVisibilityCommons
import com.bismuth.bismuth.project.visibility.ProjectVisibilityEnum
import com.bismuth.bismuth.project.visibility.ProjectVisibilityService
import com.bismuth.bismuth.user.User

class ProjectGuardian {

    lateinit var user: User;

    companion object {

        fun onUser(user: User): ProjectGuardian {
            val guardian = ProjectGuardian();
            guardian.user = user;
            return guardian;
        }

    }

    private fun basicProtectionAndGetVisibility(project: Project): ProjectVisibility {
        val projectVisibilityService = Cthulhu.getBean(ProjectVisibilityService::class.java);
        val projectVisibility = projectVisibilityService.getVisibilityOf(user, project)
                ?: throw ProjectGuardianException("You have no power over the ${project.name} project");
        return projectVisibility;
    }

    fun protectEditingOf(project: Project) {
        val projectVisibility = basicProtectionAndGetVisibility(project);
        val visibility = ProjectVisibilityCommons.getVisibilityFrom(projectVisibility);
        if (visibility == ProjectVisibilityEnum.DEVELOPER || visibility == ProjectVisibilityEnum.STAKEHOLDER)
            throw ProjectGuardianException("You don't have enough permissions to edit the ${project.name} project");
    }

    fun protectDeletionOf(project: Project) {
        val projectVisibility = basicProtectionAndGetVisibility(project);
        val visibility = ProjectVisibilityCommons.getVisibilityFrom(projectVisibility);
        if (visibility != ProjectVisibilityEnum.OWNER)
            throw ProjectGuardianException("You don't have enough permissions to delete the ${project.name} project");
    }

    fun protectAccessToRelatedUsersOf(project: Project) {
        val projectVisibility = basicProtectionAndGetVisibility(project);
        val visibility = ProjectVisibilityCommons.getVisibilityFrom(projectVisibility);
        if (visibility != ProjectVisibilityEnum.OWNER && visibility != ProjectVisibilityEnum.MANAGER)
            throw ProjectGuardianException("You don't have enough permissions to see users related to the ${project.name} project");
    }

}