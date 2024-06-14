package com.cline.api.dto

import com.cline.domain.Festival
import java.time.LocalDate

data class FestivalDetailResponse(
        val id: Long,
        val school: SchoolResponse,
        val season: String,
        val startDate: LocalDate,
        val endDate: LocalDate,
        val imageUrl: String?,
        val viewCount: Long = 0L,
        val lineUps: List<FestivalLineUpResponse>
) {

    companion object {

        fun from(festival: Festival) = with(festival) {
            FestivalDetailResponse(
                    id = id,
                    school = SchoolResponse.from(school),
                    season = season.title,
                    startDate = startDate,
                    endDate = endDate,
                    imageUrl = imageUrl,
                    viewCount = viewCount,
                    lineUps = festivalDateTimes.map { FestivalLineUpResponse.from(it) }
            )
        }
    }
}
