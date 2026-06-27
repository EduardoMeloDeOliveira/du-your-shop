package com.study.shop.uservice.core.domain

data class User(
    val username: String,
    val passwordHash: String,
    val role: String,
    val active: Boolean,
)


object Roles{
    const val CUSTOMER = "CUSTOMER"
    const val STORE_ADMIN = "STORE_ADMIN"
    val all = setOf(CUSTOMER, STORE_ADMIN)
}
