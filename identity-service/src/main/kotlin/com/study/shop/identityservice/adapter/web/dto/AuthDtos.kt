package com.study.shop.identityservice.adapter.web.dto

data class LoginRequest(
    val username: String,
    val password: String
)

data class TokenResponse(
    val token: String,
    val role: String,
    val expiresInMinutes: Long
)