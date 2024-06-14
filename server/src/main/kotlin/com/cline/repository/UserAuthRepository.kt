package com.cline.repository

import com.cline.domain.User
import com.cline.domain.UserAuth
import org.springframework.data.jpa.repository.JpaRepository

interface UserAuthRepository : JpaRepository<UserAuth, String> {
    fun deleteByUser(user: User): Long

    fun findTopByUser(user: User): UserAuth?
}
