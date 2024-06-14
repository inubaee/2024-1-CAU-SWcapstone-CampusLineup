package com.cline.application

import com.cline.domain.User
import com.cline.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

interface UserService {

    fun findById(id: Long): User

    @Component
    @Transactional
    class Default(
            private val userRepository: UserRepository
    ) : UserService {

        override fun findById(id: Long): User {
            return userRepository.findByIdOrNull(id)
                    ?: throw IllegalArgumentException("User Not Found | id: $id")
        }
    }
}
