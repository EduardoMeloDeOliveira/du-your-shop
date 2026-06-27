package com.study.shop.uservice.adapter.web

import com.study.shop.uservice.adapter.web.dto.VerifyRequest
import com.study.shop.uservice.adapter.web.dto.VerifyResponse
import com.study.shop.uservice.core.usecase.CredentialCheck
import com.study.shop.uservice.core.usecase.VerifyCredentialsUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/internal/users")
class InternalCredentialsController(
    private val verifyCredentialsUseCase: VerifyCredentialsUseCase
) {
    @PostMapping("/verify")
    fun verify(@RequestBody request: VerifyRequest): ResponseEntity<VerifyResponse> {
        val user = verifyCredentialsUseCase.verify(CredentialCheck(request.username, request.password))
            ?: return ResponseEntity.badRequest().build()

        return ResponseEntity.ok(VerifyResponse(user.username, user.role))
    }

}