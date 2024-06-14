package com.cline.domain

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
data class PostingComment(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,
        @ManyToOne(fetch = FetchType.LAZY)
        val writer: User,
        val value: String,
        @ManyToOne(fetch = FetchType.LAZY)
        val posting: Posting,
        @CreatedDate
        val createdAt: LocalDateTime = LocalDateTime.now()
)
