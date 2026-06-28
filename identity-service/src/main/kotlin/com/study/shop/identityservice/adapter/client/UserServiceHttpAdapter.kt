package com.study.shop.identityservice.adapter.client

import com.study.shop.identityservice.adapter.client.dto.UserVerifyRequest
import com.study.shop.identityservice.adapter.client.dto.UserVerifyResponse
import com.study.shop.identityservice.core.domain.AuthenticatedUser
import com.study.shop.identityservice.core.port.CredentialVerifierPort
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

@Component
class UserServiceHttpAdapter(
    private val userRestClient: RestClient
) : CredentialVerifierPort {

    override fun verify(
        username: String,
        role: String
    ): AuthenticatedUser? {
        return try {
            userRestClient.post()
                .uri("/internal/users/verify")
                .body(UserVerifyRequest(username, role))
                .retrieve()
                .body(UserVerifyResponse::class.java)
                ?.let { AuthenticatedUser(it.username, it.role) }
        } catch (e: HttpClientErrorException.Unauthorized){
            null
        }
    }
}