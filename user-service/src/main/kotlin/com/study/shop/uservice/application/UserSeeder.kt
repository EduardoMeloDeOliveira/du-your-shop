package com.study.shop.uservice.application

import com.study.shop.uservice.core.domain.Roles
import com.study.shop.uservice.core.usecase.ManageUserUseCase
import com.study.shop.uservice.core.usecase.NewUser
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class UserSeeder(
    private val manage: ManageUserUseCase,
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        if (manage.list().isNotEmpty()) return
        manage.register(NewUser("maria", "senha123", Roles.CUSTOMER))
        manage.register(NewUser("admin", "admin123", Roles.STORE_ADMIN))
    }
}
