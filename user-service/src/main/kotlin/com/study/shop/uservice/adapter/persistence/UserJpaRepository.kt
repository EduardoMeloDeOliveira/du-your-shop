package com.study.shop.uservice.adapter.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository : JpaRepository<UserEntity, String>