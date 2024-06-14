package com.cline.api

import com.cline.api.dto.IdCheckResponse
import com.cline.api.dto.LoginRequest
import com.cline.api.dto.PasswordRequest
import com.cline.api.dto.SignUpRequest
import com.cline.application.UserAuthService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
        val userAuthService: UserAuthService
) {

    @Operation(summary = "로그인")
    @PostMapping("/login")
    fun login(
            @RequestBody request: LoginRequest
    ) = userAuthService.login(id = request.id, password = request.password)

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    fun signUp(
            @RequestBody request: SignUpRequest
    ) = userAuthService.signUp(email = request.id, password = request.password, name = request.name, code = request.code)

    @Operation(summary = "아이디 중복 확인")
    @GetMapping("/id-check/{email}")
    fun idCheck(
            @PathVariable email: String
    ) = IdCheckResponse(canUse = userAuthService.canUseId(email))

    @Operation(summary = "인증메일 전송")
    @PostMapping("/mail/{email}")
    fun sendMail(
            @PathVariable email: String
    ) {
        userAuthService.sendMail(email)
    }

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping
    fun leave(
            @RequestParam userId: Long
    ) {
        userAuthService.leave(userId)
    }

    @Operation(summary = "비밀번호 변경")
    @PutMapping("/password")
    fun changePassword(
            @RequestParam userId: Long,
            @RequestBody request: PasswordRequest
    ) {
        userAuthService.changePassword(userId, request)
    }
}
