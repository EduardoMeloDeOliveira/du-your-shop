package com.study.shop.identityservice.adapter.web

import com.study.shop.identityservice.adapter.web.dto.LoginRequest
import com.study.shop.identityservice.adapter.web.dto.TokenResponse
import com.study.shop.identityservice.core.usecase.IssueTokenUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/auth")
class AuthController (
    private val issueTokenUseCase: IssueTokenUseCase
){

    @PostMapping("/token")
    fun login(@RequestBody request: LoginRequest) : TokenResponse{
        val issued = issueTokenUseCase.login(request.username,request.password)
        return TokenResponse(
            issued.token,
            issued.role,
            issued.expiresInMinutes
        )
    }
}