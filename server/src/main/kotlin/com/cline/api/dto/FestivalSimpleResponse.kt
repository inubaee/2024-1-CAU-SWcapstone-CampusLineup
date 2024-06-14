package com.cline.api.dto

import com.cline.domain.Festival
import java.time.LocalDate

data class FestivalSimpleResponse(
        val id: Long,
        val imageUrl: String?,
        val schoolName: String,
        val startDate: LocalDate,
        val endDate: LocalDate
) {

    companion object {

        fun from(festival: Festival) = with(festival) {
            FestivalSimpleResponse(
                    id = id,
                    imageUrl = imageUrl,
                    schoolName = school.name,
                    startDate = startDate,
                    endDate = endDate
            )
        }
    }
}
