package com.cline.api

import com.cline.application.UserService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
        private val userService: UserService
) {

    @Operation(summary = "유저 프로필 조회")
    @GetMapping("/{id}")
    fun getProfile(
            @PathVariable id: Long
    ) = userService.findById(id)
}
