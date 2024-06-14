package com.cline.api.dto

import com.cline.domain.FestivalDateTime
import java.time.LocalDateTime

data class FestivalDateTimeResponse(
        val school: SchoolResponse,
        val season: String,
        val startTime: LocalDateTime,
        val imageUrl: String?,
        val artists: List<ArtistResponse>
) {

    companion object {

        fun from(festivalDateTime: FestivalDateTime) = with(festivalDateTime) {
            FestivalDateTimeResponse(
                    school = SchoolResponse.from(festival.school),
                    season = festival.season.title,
                    startTime = startTime,
                    imageUrl = festival.imageUrl,
                    artists = lineUps.map { ArtistResponse.from(it.artist) }
            )
        }
    }
}
