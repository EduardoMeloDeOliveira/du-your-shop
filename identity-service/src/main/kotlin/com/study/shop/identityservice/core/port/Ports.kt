package com.study.shop.identityservice.core.port

import com.study.shop.identityservice.core.domain.AuthenticatedUser

interface CredentialVerifierPort {
    fun verify(username: String, role: String): AuthenticatedUser?
}


interface TokenIssuerPort {
    fun issue(user: AuthenticatedUser): IssuedToken
}


data class IssuedToken(
    val token: String,
    val role: String,
    val expiresInMinutes: Long
)