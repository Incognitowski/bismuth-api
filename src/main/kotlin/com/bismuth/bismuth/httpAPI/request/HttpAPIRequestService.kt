package com.bismuth.bismuth.httpAPI.request

import com.bismuth.bismuth.framework.authentication.Auth
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
@Transactional
class HttpAPIRequestService {

    @Autowired
    lateinit var httpAPIRequestRepository: HttpAPIRequestRepository;

    @Autowired
    lateinit var request: HttpServletRequest;

    fun getAllFromHttpAPI(
            httpAPIId: UUID
    ): List<HttpAPIRequest> {
        return httpAPIRequestRepository.getAllFromHttpAPI(httpAPIId);
    }

    fun create(
            httpAPIId: UUID,
            httpAPIRequest: HttpAPIRequest
    ): HttpAPIRequest {
        val user = Auth.getAuthenticatedUser(request);
        httpAPIRequest.httpApiId = httpAPIId;
        httpAPIRequest.httpRequestId = UUID.randomUUID();
        httpAPIRequest.createdBy = user;
        httpAPIRequest.ownedBy = user;
        return httpAPIRequestRepository.save(httpAPIRequest);
    }

    fun update(
            httpAPIRequestId: UUID,
            httpAPIRequest: HttpAPIRequest
    ): HttpAPIRequest {
        return httpAPIRequestRepository.save(httpAPIRequest);
    }

    fun delete(
            httpAPIRequest: HttpAPIRequest
    ) {
        httpAPIRequestRepository.delete(httpAPIRequest);
    }

    fun searchByWordInHttpAPI(httpAPIId: UUID, searchWord: String): List<HttpAPIRequest> {
        return httpAPIRequestRepository.searchByWordInHttpAPI(httpAPIId, "%${searchWord}%");
    }


}