package com.cline.domain

import jakarta.persistence.*

@Entity
data class PostingLike(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,
        @ManyToOne(fetch = FetchType.LAZY)
        val user: User,
        @ManyToOne(fetch = FetchType.LAZY)
        val posting: Posting
)
