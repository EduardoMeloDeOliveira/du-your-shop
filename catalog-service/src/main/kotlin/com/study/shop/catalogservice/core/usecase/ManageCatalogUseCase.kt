package com.study.shop.catalogservice.core.usecase

import com.study.shop.catalogservice.core.domain.Product
import com.study.shop.catalogservice.core.port.ProductRepositoryPort
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class ManageCatalogUseCase(
    private val productRepositoryPort: ProductRepositoryPort
) {

    fun create(command: NewProduct): Product {

        require(command.sku.isNotBlank()) { "sku obrigatorio" }
        require(command.name.isNotBlank()) { "name obrigatorio" }
        require(command.price > BigDecimal.ZERO) { "price deve ser > 0" }
        require(command.stock >= 0) { "stock nao pode ser negativo" }

        if (productRepositoryPort.existsBySku(command.sku)) throw DuplicateSkuException(command.sku)

        return productRepositoryPort.save(Product(command.sku, command.name, command.price, command.stock))
    }

    fun update(sku: String, commandUpdate: ProductUpdate): Product {
        val currentProduct = productRepositoryPort.findBySku(sku) ?: throw ProductNotFoundException(sku)

        commandUpdate.price?.let { require(it > BigDecimal.ZERO) { "price deve ser > 0" } }
        commandUpdate.stock?.let { require(it >= 0) { "stock nao pode ser negativo" } }
        commandUpdate.name?.let { require(it.isNotBlank()) { "name nao pode ser vazio" } }

        val updated = currentProduct.copy(
            name = commandUpdate.name ?: currentProduct.name,
            price = commandUpdate.price ?: currentProduct.price,
            stock = commandUpdate.stock ?: currentProduct.stock,
        )

        return  productRepositoryPort.save(updated)

    }

    fun findAll() : List<Product> = productRepositoryPort.findAll()

}