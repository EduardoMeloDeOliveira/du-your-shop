package com.study.shop.identityservice.application.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfig(
    @Value("\${user-service.url}") private val userServiceUrl: String,

    ) {

    @Bean
    fun userRestClient(): RestClient {
        return RestClient.builder().baseUrl(userServiceUrl).build()
    }
}