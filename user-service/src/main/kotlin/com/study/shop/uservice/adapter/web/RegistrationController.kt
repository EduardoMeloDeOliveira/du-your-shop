package com.study.shop.uservice.adapter.web

import com.study.shop.uservice.adapter.web.dto.RegisterRequest
import com.study.shop.uservice.adapter.web.dto.UserResponse
import com.study.shop.uservice.core.domain.Roles
import com.study.shop.uservice.core.usecase.ManageUserUseCase
import com.study.shop.uservice.core.usecase.NewUser
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/register")
class RegistrationController (
    private val manageUseCase: ManageUserUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody request: RegisterRequest) : UserResponse {
      return  UserResponse.from(
            manageUseCase.register(
                NewUser(request.username, request.password, Roles.CUSTOMER)
            )
        )
    }

}