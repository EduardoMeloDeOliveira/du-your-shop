package com.study.shop.identityservice.adapter.token

import com.study.shop.identityservice.core.domain.AuthenticatedUser
import com.study.shop.identityservice.core.port.IssuedToken
import com.study.shop.identityservice.core.port.TokenIssuerPort
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtTokenIssuerAdapter(
    @Value("\${jwt.secret}") private val secret: String,
    @Value("\${jwt.expiration-minutes}") private val expirationMinutes: Long
) : TokenIssuerPort{

    private val key = Keys.hmacShaKeyFor(secret.toByteArray())

    override fun issue(user: AuthenticatedUser): IssuedToken {
        val now = System.currentTimeMillis()
        val expiry = now + expirationMinutes * 60 * 1000
        val token = Jwts.builder()
            .subject(user.username)
            .claim("role", user.role)
            .issuedAt(Date(now))
            .expiration(Date(expiry))
            .signWith(key, Jwts.SIG.HS256)
            .compact()

        return IssuedToken(token, user.role,expirationMinutes)
    }

}