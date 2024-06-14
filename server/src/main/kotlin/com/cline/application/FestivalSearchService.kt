package com.cline.application

import com.cline.domain.Festival
import com.cline.domain.Location
import com.cline.repository.FestivalRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

interface FestivalSearchService {

    fun search(keyword: String?, location: Location?, startDate: LocalDate?, endDate: LocalDate?): List<Festival>

    @Component
    @Transactional
    class Default(
            private val festivalRepository: FestivalRepository,
    ) : FestivalSearchService {

        override fun search(keyword: String?, location: Location?, startDate: LocalDate?, endDate: LocalDate?): List<Festival> {
            return festivalRepository.findAll().filter { festival ->
                matchLocation(festival, location) && matchDateRange(festival, startDate, endDate) && matchKeyword(festival, keyword)
            }
        }

        private fun matchLocation(festival: Festival, location: Location?): Boolean {
            return location == null || festival.school.location == location
        }

        private fun matchDateRange(festival: Festival, startDate: LocalDate?, endDate: LocalDate?): Boolean {
            return startDate == null || endDate == null || festival.isInPeriod(startDate, endDate)
        }

        private fun matchKeyword(festival: Festival, keyword: String?): Boolean {
            return keyword.isNullOrBlank() ||
                    matchSchoolName(festival, keyword) ||
                    matchArtist(festival, keyword)
        }

        private fun matchSchoolName(festival: Festival, keyword: String): Boolean {
            return festival.school.name.contains(keyword, ignoreCase = true)
        }

        private fun matchArtist(festival: Festival, keyword: String): Boolean {
            return festival.festivalDateTimes.any { festivalDateTime ->
                festivalDateTime.lineUps.any { lineUp ->
                    lineUp.artist.name.contains(keyword, ignoreCase = true)
                }
            }
        }
    }
}
