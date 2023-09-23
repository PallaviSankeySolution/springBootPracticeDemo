package com.example.studGradle.interceptor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class InterceptorConfig @Autowired constructor(requestLoggingInterceptor: RequestHandlingInterceptor) :
    WebMvcConfigurer {
    private val requestLoggingInterceptor: RequestHandlingInterceptor

    init {
        this.requestLoggingInterceptor = requestLoggingInterceptor
        println("inside InterceptorConfig init **********************")
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        println("enter in addInterceptor method ********8")
        registry.addInterceptor(requestLoggingInterceptor)
    }
}