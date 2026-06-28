package com.study.shop.catalogservice.application

import com.study.shop.catalogservice.core.usecase.ManageCatalogUseCase
import com.study.shop.catalogservice.core.usecase.NewProduct
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class CatalogSeeder(
    private val manage: ManageCatalogUseCase,
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        if (manage.findAll().isNotEmpty()) return
        manage.create(NewProduct("CAMISETA", "Camiseta", BigDecimal("49.90"), 100))
        manage.create(NewProduct("CANECA", "Caneca", BigDecimal("29.90"), 200))
    }
}