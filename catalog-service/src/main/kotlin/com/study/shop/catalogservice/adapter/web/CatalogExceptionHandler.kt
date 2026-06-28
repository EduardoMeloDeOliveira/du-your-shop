package com.study.shop.catalogservice.adapter.web

import com.study.shop.catalogservice.core.usecase.DuplicateSkuException
import com.study.shop.catalogservice.core.usecase.ProductNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CatalogExceptionHandler {

    @ExceptionHandler(ProductNotFoundException::class)
    fun notFound(e: ProductNotFoundException): ResponseEntity<Map<String, String>> =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to (e.message ?: "nao encontrado")))

    @ExceptionHandler(DuplicateSkuException::class)
    fun duplicate(e: DuplicateSkuException): ResponseEntity<Map<String, String>> =
        ResponseEntity.status(HttpStatus.CONFLICT).body(mapOf("error" to (e.message ?: "conflito")))

    @ExceptionHandler(IllegalArgumentException::class)
    fun badRequest(e: IllegalArgumentException): ResponseEntity<Map<String, String>> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to (e.message ?: "requisicao invalida")))
}