package com.study.shop.catalogservice.adapter.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface ProductJpaRepository : JpaRepository<ProductEntity, String> {
}