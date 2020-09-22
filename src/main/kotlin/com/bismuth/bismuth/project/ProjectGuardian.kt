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

    fun protectUserDetachmentFrom(project: Project, visibilityToRemove: ProjectVisibility) {
        val currentUsersVisibility = basicProtectionAndGetVisibility(project);
        val currentUserIsOwnerOrManager = listOf(ProjectVisibilityEnum.OWNER, ProjectVisibilityEnum.MANAGER)
                .contains(currentUsersVisibility.getVisibilityAsEnum());
        val isManagerAndTryingToRemoveAnotherManager =
                currentUsersVisibility.getVisibilityAsEnum() == ProjectVisibilityEnum.MANAGER &&
                        visibilityToRemove.getVisibilityAsEnum() == ProjectVisibilityEnum.MANAGER;
        if (!currentUserIsOwnerOrManager)
            throw ProjectGuardianException("You don't have enough permissions to manage users related to the ${project.name} project");
        if (visibilityToRemove.getVisibilityAsEnum() == ProjectVisibilityEnum.OWNER)
            throw ProjectGuardianException("You can't detach the owner of the ${project.name} project from his project.");
        if (isManagerAndTryingToRemoveAnotherManager)
            throw ProjectGuardianException("Only owners can detach managers from projects.");
    }

    fun protectOwnershipTransferOf(project: Project) {
        val currentUsersVisibility = basicProtectionAndGetVisibility(project);
        val currentUserIsOwner = listOf(ProjectVisibilityEnum.OWNER)
                .contains(currentUsersVisibility.getVisibilityAsEnum());
        if (!currentUserIsOwner)
            throw ProjectGuardianException("Only the owner of the project can transfer it's ownership.");
    }

    fun protectAccessingAppsOf(project: Project) {
        if (project.isPubliclyVisible) return;
        val currentUsersVisibility = basicProtectionAndGetVisibility(project);
        val currentUserHasRoleInApplication = listOf(
                ProjectVisibilityEnum.OWNER,
                ProjectVisibilityEnum.MANAGER,
                ProjectVisibilityEnum.STAKEHOLDER,
                ProjectVisibilityEnum.DEVELOPER
        ).contains(currentUsersVisibility.getVisibilityAsEnum());
        if (!currentUserHasRoleInApplication)
            throw ProjectGuardianException("You don't have access to this project.");
    }

}