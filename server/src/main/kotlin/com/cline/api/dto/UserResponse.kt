package com.cline.api.dto

import com.cline.domain.User

data class UserResponse(
        val id: Long
) {

    companion object {

        fun from(user: User) = with(user) {
            UserResponse(
                    id = id
            )
        }
    }
}
