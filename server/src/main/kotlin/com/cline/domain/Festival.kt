package com.cline.domain

import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class Festival(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,
        @ManyToOne(fetch = FetchType.LAZY)
        val school: School,
        @Enumerated(value = EnumType.STRING)
        val season: Season,
        val startDate: LocalDate,
        val endDate: LocalDate,
        val imageUrl: String?,
        var viewCount: Long = 0L,
        @OneToMany(mappedBy = "festival", fetch = FetchType.LAZY)
        val festivalDateTimes: List<FestivalDateTime> = emptyList()
) {

    fun isInPeriod(startDate: LocalDate, endDate: LocalDate): Boolean {
        return (this.endDate.isBefore(startDate) || this.startDate.isAfter(endDate)).not()
    }

    fun increaseViewCount() {
        viewCount += 1
    }
}
