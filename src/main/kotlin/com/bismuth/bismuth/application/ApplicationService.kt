package com.bismuth.bismuth.application

import com.bismuth.bismuth.application.events.ApplicationEventService
import com.bismuth.bismuth.application.visibility.ApplicationVisibilityService
import com.bismuth.bismuth.framework.authentication.Auth
import com.bismuth.bismuth.framework.data.PageCommons
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityNotFoundException
import javax.servlet.http.HttpServletRequest

@Service
class ApplicationService {

    @Autowired
    lateinit var request: HttpServletRequest;

    @Autowired
    lateinit var applicationRepository: ApplicationRepository;

    @Autowired
    lateinit var applicationEventService: ApplicationEventService;

    @Autowired
    lateinit var applicationVisibilityService: ApplicationVisibilityService;

    fun getById(applicationId: UUID): Application {
        val application = applicationRepository.findById(applicationId);
        if (!application.isPresent)
            throw EntityNotFoundException("We were unable to find an application with such identifier.");
        return application.get();
    }

    fun getAllWithProjectId(projectId: UUID, pageable: Pageable): Page<Application> {
        val user = Auth.getAuthenticatedUser(request);
        val applications = applicationRepository.getByProjectId(projectId, user.user_id!!);
        return PageCommons.getPaged(pageable, applications);
    }

    fun create(application: Application): Application {
        val user = Auth.getAuthenticatedUser(request);
        application.applicationId = UUID.randomUUID();
        application.createdBy = user.user_id;
        application.ownedBy = user.user_id;
        ApplicationBO.validate(application);
        val newApplication = applicationRepository.save(application);
        applicationEventService.createNewProjectEvent(newApplication);
        applicationVisibilityService.createNewApplicationVisibility(newApplication);
        return newApplication;
    }

    fun update(application: Application, applicationId: UUID): Application {
        val originalApplication = getById(applicationId).copy();
        val user = Auth.getAuthenticatedUser(request);
        ApplicationBO.validate(application);
        ApplicationGuardian.onUser(user).protectEditingOf(application);
        applicationRepository.save(application);
        applicationEventService.createEventsForUpdate(application, originalApplication);
        return application;
    }

}