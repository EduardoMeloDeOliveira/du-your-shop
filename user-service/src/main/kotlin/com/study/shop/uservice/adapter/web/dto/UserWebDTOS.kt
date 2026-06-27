package com.study.shop.uservice.adapter.web.dto

import com.study.shop.uservice.core.domain.User


data class RegisterRequest (
    val username: String,
    val password: String
)


data class CreateUserRequest(
    val username: String,
    val password: String,
    val role: String,
)

data class SetActiveUserRequest(
    val active: Boolean
)

data class VerifyRequest(
    val username: String,
    val password: String
)

data class UserResponse(
    val username: String,
    val role: String,
    val active: Boolean
){
    companion object{
        fun from(user: User)  = UserResponse(
            username = user.username,
            role = user.role,
            active = user.active
        )
    }
}


data class VerifyResponse(
    val username: String,
    val role: String,

)