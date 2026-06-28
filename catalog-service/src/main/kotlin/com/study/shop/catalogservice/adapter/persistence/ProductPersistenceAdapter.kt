package com.study.shop.catalogservice.adapter.persistence

import com.study.shop.catalogservice.core.domain.Product
import com.study.shop.catalogservice.core.port.ProductRepositoryPort

class ProductPersistenceAdapter(
    private val productRepository: ProductJpaRepository
) : ProductRepositoryPort {

    override fun save(product: Product): Product {
        return productRepository.save(product.toEntity()).toDomain()
    }

    override fun findBySku(sku: String): Product? {
        return productRepository.findById(sku).map { it.toDomain() }.orElse(null)
    }

    override fun findAll(): List<Product> {
        return productRepository.findAll().map{it.toDomain()}
    }

    override fun existsBySku(sku: String): Boolean {
        return productRepository.existsById(sku)
    }


    private fun Product.toEntity() = ProductEntity(sku, name, price, stock)
    private fun ProductEntity.toDomain() = Product(sku, name, price, stock)
}