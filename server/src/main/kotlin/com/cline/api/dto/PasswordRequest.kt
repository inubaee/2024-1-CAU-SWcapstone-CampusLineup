package com.cline.api.dto

data class PasswordRequest(
        val oldPassword: String,
        val newPassword: String
)
