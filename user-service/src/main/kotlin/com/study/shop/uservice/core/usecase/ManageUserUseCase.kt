package com.study.shop.uservice.core.usecase

import com.study.shop.uservice.core.domain.Roles
import com.study.shop.uservice.core.domain.User
import com.study.shop.uservice.core.port.PasswordHasherPort
import com.study.shop.uservice.core.port.UserRepositoryPort
import org.springframework.stereotype.Service


@Service
class ManageUserUseCase(
    private val userRepositoryPort: UserRepositoryPort,
    private val passwordHasherPort: PasswordHasherPort,
) {

    fun register(command: NewUser): User {
        require(command.username.isNotBlank()) { "Username is required" }
        require(command.rawPassword.isNotBlank()) { "Password is required" }
        require(command.rawPassword.length > 6) { "Password must be at least 6 characters long" }
        require(command.role in Roles.all) { "Invalid role" }

        return userRepositoryPort.save(
            User(
                username = command.username,
                passwordHash = passwordHasherPort.hash(command.rawPassword),
                role = command.role,
                active = true,
            )
        )
    }

    fun list(): List<User> = userRepositoryPort.findAll()


    fun setActive(userName: String, active: Boolean) : User {
        val user = userRepositoryPort.findByUsername(userName) ?: throw UserNotFoundExecpetion(userName)
        return userRepositoryPort.save(user.copy(active = active))
    }

}