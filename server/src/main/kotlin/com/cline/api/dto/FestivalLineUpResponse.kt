package com.cline.api.dto

import com.cline.domain.FestivalDateTime
import java.time.LocalDateTime

data class FestivalLineUpResponse(
        val startTime: LocalDateTime,
        val artists: List<ArtistResponse>
) {

    companion object {

        fun from(festivalDateTime: FestivalDateTime) = with(festivalDateTime) {
            FestivalLineUpResponse(
                    startTime = startTime,
                    artists = lineUps.map { ArtistResponse.from(it.artist) }
            )
        }
    }
}
