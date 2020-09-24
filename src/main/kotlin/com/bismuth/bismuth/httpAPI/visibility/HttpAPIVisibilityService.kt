package com.bismuth.bismuth.httpAPI.visibility

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class HttpAPIVisibilityService {

    @Autowired
    lateinit var httpAPIVisibilityRepository: HttpAPIVisibilityRepository;

    fun create(
            httpAPIVisibility: HttpAPIVisibility
    ): HttpAPIVisibility {
        httpAPIVisibility.httpApiVisibilityId = UUID.randomUUID();
        return httpAPIVisibilityRepository.save(httpAPIVisibility);
    }

}