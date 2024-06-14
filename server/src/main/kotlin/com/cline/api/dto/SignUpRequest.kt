package com.cline.api.dto

data class SignUpRequest(
        val id: String,
        val password: String,
        val name: String,
        val code: String
)
