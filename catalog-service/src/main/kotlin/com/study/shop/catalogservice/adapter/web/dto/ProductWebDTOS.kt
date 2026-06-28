package com.study.shop.catalogservice.adapter.web.dto

import com.study.shop.catalogservice.core.domain.Product
import java.math.BigDecimal

data class CreateProductRequest(
    val sku: String,
    val name: String,
    val price: BigDecimal,
    val stock: Int
)

data class UpdateProductRequest(
    val name: String? = null,
    val price: BigDecimal? = null,
    val stock: Int? = null
)

data class ProductResponse(
    val sku: String,
    val name: String,
    val price : BigDecimal,
    val stock: Int
){
    companion object{
        fun from(product: Product) = ProductResponse(product.sku, product.name, product.price, product.stock)
    }
}