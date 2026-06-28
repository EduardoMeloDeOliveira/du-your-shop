package com.study.shop.identityservice.core.usecase

import com.study.shop.identityservice.core.port.CredentialVerifierPort
import com.study.shop.identityservice.core.port.IssuedToken
import com.study.shop.identityservice.core.port.TokenIssuerPort
import org.springframework.stereotype.Service


class InvalidCredentialException : RuntimeException("Invalid credentials")


@Service
class IssueTokenUseCase (
    private val credentialVerifierPort: CredentialVerifierPort,
    private val tokenIssuerPort: TokenIssuerPort
){
    fun login(username: String, password: String) : IssuedToken {
        require(username.isNotBlank()) {"Username is required"}
        require(password.isNotBlank()) {"Password is required"}

        val user = credentialVerifierPort.verify(username, password) ?: throw InvalidCredentialException()

        return tokenIssuerPort.issue(user)
    }
}