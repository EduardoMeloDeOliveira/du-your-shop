package com.study.shop.uservice.adapter.web

import com.study.shop.uservice.adapter.web.dto.CreateUserRequest
import com.study.shop.uservice.adapter.web.dto.SetActiveUserRequest
import com.study.shop.uservice.adapter.web.dto.UserResponse
import com.study.shop.uservice.core.usecase.ManageUserUseCase
import com.study.shop.uservice.core.usecase.NewUser
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/users")
class UserAdminController(
    private val manageUserUseCase: ManageUserUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: CreateUserRequest) : UserResponse {

        return UserResponse.from(
            manageUserUseCase.register(NewUser(request.username, request.password, request.role))
        )
    }


    @GetMapping
    fun listUser() : List<UserResponse> {

        return manageUserUseCase.list().map { UserResponse.from(it) }
    }

    @PostMapping("/{username}/active")
    open fun setActive(@PathVariable username: String, @RequestBody request: SetActiveUserRequest) : UserResponse {
        return UserResponse.from(
            manageUserUseCase.setActive(username, request.active)
        )
    }
}