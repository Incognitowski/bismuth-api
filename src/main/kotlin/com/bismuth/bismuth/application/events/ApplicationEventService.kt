package com.bismuth.bismuth.application.events

import com.bismuth.bismuth.application.Application
import com.bismuth.bismuth.framework.authentication.Auth
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
class ApplicationEventService {

    @Autowired
    lateinit var request: HttpServletRequest;

    @Autowired
    lateinit var applicationEventRepository: ApplicationEventRepository;

    fun create(applicationEvent: ApplicationEvent): ApplicationEvent {
        applicationEvent.applicationEventId = UUID.randomUUID();
        return applicationEventRepository.save(applicationEvent);
    }

    fun createNewProjectEvent(application: Application) {
        val user = Auth.getAuthenticatedUser(request);
        create(ApplicationEvent(
                null,
                application.applicationId!!,
                user.userId!!,
                "User ${user.username} created the '${application.name}' application. Great! ✨"
        ));
    }

    fun createEventsForUpdate(application: Application, originalApplication: Application) {
        val user = Auth.getAuthenticatedUser(request);
        val listOfEventsToCreate: List<String> = listOf();
        if (application.isPubliclyVisible != originalApplication.isPubliclyVisible) {
            listOfEventsToCreate.plus(
                    if (originalApplication.isPubliclyVisible)
                        "User ${user.username} changed the visibility of this application. This app is not available to unauthorized users anymore."
                    else
                        "User ${user.username} made this application publicly visible."
            )
        }
        if (application.name != originalApplication.name) {
            listOfEventsToCreate.plus(
                    "User ${user.username} changed the name of this application. It used to be '${originalApplication.name}', but now is '${application.name}'."
            )
        }
        if (application.isSoftdeleted != originalApplication.isSoftdeleted) {
            listOfEventsToCreate.plus(
                    "User ${user.username} deleted the ${application.name} application. RIP ${application.name} application 😭"
            )
        }
        for (event: String in listOfEventsToCreate) {
            create(ApplicationEvent(
                    null,
                    application.applicationId!!,
                    user.userId!!,
                    event
            ))
        }
    }

}

