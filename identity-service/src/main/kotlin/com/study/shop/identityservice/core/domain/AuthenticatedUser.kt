package com.study.shop.identityservice.core.domain

data class AuthenticatedUser(
    val username: String,
    var role: String
)
