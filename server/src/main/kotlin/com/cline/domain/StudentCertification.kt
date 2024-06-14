package com.cline.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
data class StudentCertification(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,
        val code: String,
        val email: String,
        @CreatedDate
        val createdAt: LocalDateTime = LocalDateTime.now()
)
