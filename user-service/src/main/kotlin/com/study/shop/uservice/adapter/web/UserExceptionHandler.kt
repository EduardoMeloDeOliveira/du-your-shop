package com.study.shop.uservice.adapter.web

import com.study.shop.uservice.core.usecase.DuplicatedUsernameException
import com.study.shop.uservice.core.usecase.UserNotFoundExecpetion
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundExecpetion::class)
    fun notFound(e: UserNotFoundExecpetion): ResponseEntity<Map<String, String>> =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to (e.message ?: "nao encontrado")))

    @ExceptionHandler(DuplicatedUsernameException::class)
    fun duplicate(e: DuplicatedUsernameException): ResponseEntity<Map<String, String>> =
        ResponseEntity.status(HttpStatus.CONFLICT).body(mapOf("error" to (e.message ?: "conflito")))

    @ExceptionHandler(IllegalArgumentException::class)
    fun badRequest(e: IllegalArgumentException): ResponseEntity<Map<String, String>> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to (e.message ?: "requisicao invalida")))

}