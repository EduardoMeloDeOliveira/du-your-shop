package com.study.shop.catalogservice.core.usecase

import java.math.BigDecimal

data class NewProduct(
    val sku: String,
    val name: String,
    val price: BigDecimal,
    val stock: Int
)

data class ProductUpdate(
    val name: String? = null,
    val price: BigDecimal? = null,
    val stock: Int? = null
)

class ProductNotFoundException(sku: String) : RuntimeException("product not found")
class DuplicateSkuException(sku: String) : RuntimeException("product with sku: $sku already exists")