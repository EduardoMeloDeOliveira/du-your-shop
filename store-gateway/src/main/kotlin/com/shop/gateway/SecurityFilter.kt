package com.shop.gateway

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter
import org.springframework.security.web.server.SecurityWebFilterChain
import javax.crypto.spec.SecretKeySpec


@Configuration
class SecurityFilter(
    @Value("\${jwt.secret}")
    private val secretKey: String
) {

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity) : SecurityWebFilterChain {

        return http
            .csrf { it.disable() }
            .authorizeExchange { exchangeSpec ->
                exchangeSpec.pathMatchers("/auth/**").permitAll()
                exchangeSpec.pathMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                exchangeSpec.pathMatchers("/api/admin/**").hasRole("STORE_ADMIN")
                exchangeSpec.anyExchange().authenticated()
            }
            .oauth2ResourceServer {  rs ->
                rs.jwt { it.jwtAuthenticationConverter(jwtAuthenticationConverter()) }
            }
            .build()
    }


    @Bean
    private fun jwtAuthenticationConverter() : ReactiveJwtAuthenticationConverter {
        val authoritiesConverter = JwtGrantedAuthoritiesConverter().apply {
            setAuthorityPrefix("ROLE_")
            setAuthoritiesClaimName("role")
        }

        val converter = ReactiveJwtAuthenticationConverter()
        converter.setJwtGrantedAuthoritiesConverter { jwt ->
            reactor.core.publisher.Flux.fromIterable(authoritiesConverter.convert(jwt) ?: emptyList())
        }

        return converter
    }


    @Bean
    fun jwtDecoder(): ReactiveJwtDecoder {

        val key = SecretKeySpec(secretKey.toByteArray(), "HmacSHA256")
        return NimbusReactiveJwtDecoder.withSecretKey(key).build()
    }

}