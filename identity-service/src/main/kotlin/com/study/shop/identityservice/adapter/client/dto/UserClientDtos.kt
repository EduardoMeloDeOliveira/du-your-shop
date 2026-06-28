package com.study.shop.identityservice.adapter.client.dto

data class UserVerifyRequest(
    val username: String,
    val password: String
)

data class UserVerifyResponse(
    val username: String,
    val role: String
)
