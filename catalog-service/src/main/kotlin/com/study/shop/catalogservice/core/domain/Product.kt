package com.study.shop.catalogservice.core.domain

import java.math.BigDecimal

data class Product (
    val sku: String,
    val name: String,
    val price: BigDecimal,
    val stock: Int
) {
}