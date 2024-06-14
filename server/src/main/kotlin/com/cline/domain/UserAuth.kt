package com.cline.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToOne

@Entity
data class UserAuth(
        @Id
        var id: String,
        var password: String,
        @OneToOne
        val user: User,
) {

    fun changePassword(newPassword: String) {
        this.password = newPassword
    }
}
