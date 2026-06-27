package com.study.shop.uservice.core.port

interface PasswordHasherPort {

    fun hash(password: String): String
    fun matches(raw: String, hash: String): Boolean
}