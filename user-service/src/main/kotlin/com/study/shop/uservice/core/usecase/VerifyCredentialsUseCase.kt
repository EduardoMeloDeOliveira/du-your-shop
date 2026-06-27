package com.study.shop.uservice.core.usecase

import com.study.shop.uservice.core.domain.User
import com.study.shop.uservice.core.port.PasswordHasherPort
import com.study.shop.uservice.core.port.UserRepositoryPort
import org.springframework.stereotype.Service

@Service
class VerifyCredentialsUseCase (
    private val userRepositoryPort: UserRepositoryPort,
    private val passwordHasherPort: PasswordHasherPort,
) {

    fun verify(command: CredentialCheck): User?{
        val user = userRepositoryPort.findByUsername(command.username) ?: return null
        if(!user.active) return null
        if(!passwordHasherPort.matches(command.rawPassword, user.passwordHash)) return null
        return user
    }
}