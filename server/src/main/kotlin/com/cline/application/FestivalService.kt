package com.cline.application

import com.cline.domain.Festival
import com.cline.domain.Season
import com.cline.repository.FestivalRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

interface FestivalService {

    fun findById(id: Long): Festival
    fun findPopularFestivals(): List<Festival>
    fun findByIdWithCounting(id: Long): Festival

    @Component
    @Transactional
    class Default(
            private val festivalRepository: FestivalRepository,
    ) : FestivalService {

        override fun findById(id: Long): Festival {
            return festivalRepository.findByIdOrNull(id)
                    ?: throw IllegalArgumentException("Festival Not Found | id : $id")
        }

        override fun findPopularFestivals(): List<Festival> {
            val season = Season._2024_봄축제
            return festivalRepository.findAllBySeasonOrderByViewCountDesc(season)
        }

        override fun findByIdWithCounting(id: Long): Festival {
            val festival = findById(id)
            festival.increaseViewCount()
            return festival
        }
    }
}
