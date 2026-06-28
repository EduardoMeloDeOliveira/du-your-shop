package com.study.shop.catalogservice.core.usecase

import com.study.shop.catalogservice.core.domain.Product
import com.study.shop.catalogservice.core.port.ProductRepositoryPort

class BrowseCatalogUseCase(
    private val productRepositoryPort: ProductRepositoryPort
) {

    fun list(): List<Product> {
        return productRepositoryPort.findAll()
    }

    fun findBySku(sku: String): Product {
        return productRepositoryPort.findBySku(sku) ?: throw ProductNotFoundException(sku)
    }
}