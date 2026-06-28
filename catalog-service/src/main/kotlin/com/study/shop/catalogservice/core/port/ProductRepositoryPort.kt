package com.study.shop.catalogservice.core.port

import com.study.shop.catalogservice.core.domain.Product

interface ProductRepositoryPort {

    fun save(product: Product) : Product
    fun findBySku(sku: String) : Product?
    fun findAll(): List<Product>
    fun existsBySku(sku: String) : Boolean
}