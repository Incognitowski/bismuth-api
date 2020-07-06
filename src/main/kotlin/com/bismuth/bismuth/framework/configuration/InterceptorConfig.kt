package com.bismuth.bismuth.framework.configuration

import com.bismuth.bismuth.framework.interceptor.AuthInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@EnableWebMvc
@Configuration
class InterceptorConfig : WebMvcConfigurer {

    @Autowired
    lateinit var authInterceptor: AuthInterceptor;

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authInterceptor).excludePathPatterns(AuthInterceptor.excludePaths());
    }

}