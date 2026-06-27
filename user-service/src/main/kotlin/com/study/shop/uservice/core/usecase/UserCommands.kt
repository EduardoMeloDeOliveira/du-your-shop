package com.study.shop.uservice.core.usecase

data class NewUser(
    val username: String,
    val rawPassword: String,
    val role: String,
)

data class CredentialCheck(
    val username: String,
    val rawPassword: String,
)

class UserNotFoundExecpetion(username: String) : RuntimeException("User $username not found")
class DuplicatedUsernameException(username: String) : RuntimeException("User $username already exists")