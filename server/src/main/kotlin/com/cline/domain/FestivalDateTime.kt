package com.cline.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class FestivalDateTime(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @ManyToOne(fetch = FetchType.LAZY)
        val festival: Festival,
        val startTime: LocalDateTime,
        @OneToMany(mappedBy = "festivalDateTime", fetch = FetchType.LAZY)
        val lineUps: List<LineUp>
)
