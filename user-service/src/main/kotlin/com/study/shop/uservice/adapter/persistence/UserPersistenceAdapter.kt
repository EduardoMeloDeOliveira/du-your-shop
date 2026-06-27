package com.study.shop.uservice.adapter.persistence

import com.study.shop.uservice.core.domain.User
import com.study.shop.uservice.core.port.UserRepositoryPort
import org.springframework.stereotype.Component

@Component
class UserPersistenceAdapter(
    private val userRepository: UserJpaRepository
) : UserRepositoryPort {


    override fun save(user: User): User = userRepository.save(user.toEntity()).toDomain()


    override fun findByUsername(username: String): User? =
        userRepository.findById(username).map { it.toDomain() }.orElse(null)

    override fun findAll(): List<User>  = userRepository.findAll().map { it.toDomain() }



    override fun existByUsername(username: String): Boolean = userRepository.existsById(username)


    private fun User.toEntity() = UserEntity(username, passwordHash, role, active)
    private fun UserEntity.toDomain() = User(username, passwordHash, role, active)
}