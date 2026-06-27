package com.study.shop.uservice.adapter.persistence

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "tb_user")
data class UserEntity(

    @Id
    @Column(nullable = false, updatable = false)
    var username: String = "",

    @Column(name = "password_hash", nullable = false)
    var passwordHash: String = "",

    @Column(nullable = false)
    var role: String = "",

    @Column(nullable = false)
    var active: Boolean = true,


    )
