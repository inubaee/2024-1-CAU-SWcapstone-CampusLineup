package com.cline.domain

import jakarta.persistence.*

@Entity
data class School(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,
        val name: String,
        @Enumerated(value = EnumType.STRING)
        @Column(length = 50)
        val location: Location,
        val instagram: String? = null,
        val domain: String?
)
