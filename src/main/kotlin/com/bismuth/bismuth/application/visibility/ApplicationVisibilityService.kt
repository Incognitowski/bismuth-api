package com.bismuth.bismuth.application.visibility

import com.bismuth.bismuth.application.Application
import com.bismuth.bismuth.framework.authentication.Auth
import com.bismuth.bismuth.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
class ApplicationVisibilityService {

    @Autowired
    lateinit var request: HttpServletRequest;

    @Autowired
    lateinit var applicationVisibilityRepository: ApplicationVisibilityRepository;

    fun create(applicationVisibility: ApplicationVisibility): ApplicationVisibility {
        applicationVisibility.applicationVisibilityId = UUID.randomUUID();
        return applicationVisibilityRepository.save(applicationVisibility);
    }

    fun createNewApplicationVisibility(application: Application) {
        val user = Auth.getAuthenticatedUser(request);
        create(ApplicationVisibility(
                null,
                application.applicationId!!,
                user.userId!!,
                "OWNER"
        ))
    }

    fun getVisibilityOf(application: Application, user: User): ApplicationVisibility? {
        return applicationVisibilityRepository.getVisibilityOf(user.userId!!, application.applicationId!!);
    }

}