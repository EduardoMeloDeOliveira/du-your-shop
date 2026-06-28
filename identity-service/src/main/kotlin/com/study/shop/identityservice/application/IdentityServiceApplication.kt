package com.study.shop.identityservice.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication(scanBasePackages = ["com.study.shop.identityservice"])
class IdentityServiceApplication {
    fun main(args: Array<String>) {
        runApplication<IdentityServiceApplication>(*args)
    }
}