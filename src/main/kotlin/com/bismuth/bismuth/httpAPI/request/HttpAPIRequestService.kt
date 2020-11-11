package com.bismuth.bismuth.httpAPI.request

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class HttpAPIRequestService {

    @Autowired
    lateinit var httpAPIRequestRepository: HttpAPIRequestRepository;

    fun getAllFromHttpAPI(
            httpAPIId: UUID
    ): List<HttpAPIRequest> {
        return httpAPIRequestRepository.getAllFromHttpAPI(httpAPIId);
    }

    fun create(
            httpAPIId: UUID,
            httpAPIRequest: HttpAPIRequest
    ): HttpAPIRequest {
        httpAPIRequest.httpApiId = httpAPIId;
        httpAPIRequest.httpRequestId = UUID.randomUUID();
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