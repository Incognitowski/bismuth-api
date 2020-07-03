package com.bismuth.bismuth.framework.configuration

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.CorsRegistry

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Component
class CORSConfig {

    @Bean
    fun corsConfigurer(): WebMvcConfigurer? {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry
                        .addMapping("/*")
                        .allowedOrigins("http://localhost:8080")
            }
        }
    }

}