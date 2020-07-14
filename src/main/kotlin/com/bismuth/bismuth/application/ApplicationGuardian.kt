package com.bismuth.bismuth.application

import com.bismuth.bismuth.application.exceptions.ApplicationGuardianException
import com.bismuth.bismuth.application.visibility.ApplicationVisibility
import com.bismuth.bismuth.application.visibility.ApplicationVisibilityService
import com.bismuth.bismuth.framework.Cthulhu
import com.bismuth.bismuth.project.exceptions.ProjectGuardianException
import com.bismuth.bismuth.user.User

class ApplicationGuardian {

    lateinit var user: User;

    companion object {

        fun onUser(user: User): ApplicationGuardian {
            val guardian = ApplicationGuardian();
            guardian.user = user;
            return guardian;
        }

    }

    fun basicProtectionAndGetVisibility(application: Application): ApplicationVisibility {
        val applicationVisibilityService = Cthulhu.getBean(ApplicationVisibilityService::class.java);
        val applicationVisibility = applicationVisibilityService.getVisibilityOf(application, user)
                ?: throw ProjectGuardianException("You have no power over the ${application.name} application");
        return applicationVisibility;
    }

    fun protectEditingOf(application: Application) {
        val applicationVisibility = basicProtectionAndGetVisibility(application);
        if (applicationVisibility.visibility == "DEVELOPER" || applicationVisibility.visibility == "STAKEHOLDER")
            throw ApplicationGuardianException("You don't have enough permissions to edit the ${application.name} application");
    }

    fun protectDeletionOf(application: Application) {
        val applicationVisibility = basicProtectionAndGetVisibility(application);
        if (applicationVisibility.visibility != "OWNER")
            throw ApplicationGuardianException("You don't have enough permissions to delete the ${application.name} application");
    }


}