package com.study.shop.uservice.adapter.security

import com.study.shop.uservice.core.port.PasswordHasherPort
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordEncoderAdapter : PasswordHasherPort {

   private val encoder = BCryptPasswordEncoder()

    override fun hash(password: String): String = encoder.encode(password)

    override fun matches(raw: String, hash: String): Boolean = encoder.matches(raw, hash)
}