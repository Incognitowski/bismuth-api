package com.bismuth.bismuth.httpAPI

import com.bismuth.bismuth.framework.authentication.Auth
import com.bismuth.bismuth.httpAPI.visibility.HttpAPIVisibility
import com.bismuth.bismuth.httpAPI.visibility.HttpAPIVisibilityConstants
import com.bismuth.bismuth.httpAPI.visibility.HttpAPIVisibilityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
@Transactional
class HttpAPIService {

    @Autowired
    lateinit var request: HttpServletRequest;

    @Autowired
    lateinit var httpAPIRepository: HttpAPIRepository;

    @Autowired
    lateinit var httpAPIVisibilityService: HttpAPIVisibilityService;

    fun create(
            projectId: UUID,
            applicationId: UUID,
            httpAPI: HttpAPI
    ): HttpAPI {
        val user = Auth.getAuthenticatedUser(request);
        httpAPI.httpApiId = UUID.randomUUID();
        httpAPI.applicationId = applicationId;
        httpAPI.createdBy = user;
        httpAPI.ownedBy = user;
        val createdHttpAPI = httpAPIRepository.save(httpAPI);
        httpAPIVisibilityService.create(
                HttpAPIVisibility(
                        null,
                        createdHttpAPI.httpApiId,
                        user.userId!!,
                        HttpAPIVisibilityConstants.OWNER
                )
        )
        return createdHttpAPI;
    }

    fun getAllVisibleToUser(
            projectId: UUID,
            applicationId: UUID
    ): List<HttpAPI> {
        val user = Auth.getAuthenticatedUser(request);
        return httpAPIRepository.getAllVisibleToUser(applicationId, user.userId!!);
    }

    fun getById(httpAPIId: UUID): HttpAPI {
        return httpAPIRepository.findById(httpAPIId).get();
    }

}