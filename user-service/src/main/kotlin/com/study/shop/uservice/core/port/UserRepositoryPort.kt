package com.study.shop.uservice.core.port

import com.study.shop.uservice.core.domain.User

interface UserRepositoryPort {

    fun save(user: User) : User
    fun findByUsername(username: String) : User?
    fun findAll(): List<User>
    fun existByUsername(username: String) : Boolean
}