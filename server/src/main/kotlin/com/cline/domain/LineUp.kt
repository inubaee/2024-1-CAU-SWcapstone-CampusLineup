package com.cline.domain

import jakarta.persistence.*

@Entity
data class LineUp(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @ManyToOne(fetch = FetchType.LAZY)
        val artist: Artist,
        @ManyToOne(fetch = FetchType.LAZY)
        val festivalDateTime: FestivalDateTime
)
