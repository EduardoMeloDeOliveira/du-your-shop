package com.study.shop.catalogservice.adapter.persistence

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "tb_product")
class ProductEntity(

    @Id
    @Column(nullable = false, updatable = false)
    var sku: String = "",

    @Column(nullable = false)
    var name: String = "",

    @Column(nullable = false)
    var price: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    var stock : Int = 0

)
