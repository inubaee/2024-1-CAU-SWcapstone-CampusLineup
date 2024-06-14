package com.cline.api.dto

import com.cline.domain.Artist

data class ArtistResponse(
        val id: Long,
        val name: String,
        val imageUrl: String?
) {

    companion object {

        fun from(artist: Artist) = with(artist) {
            ArtistResponse(
                    id = id,
                    name = name,
                    imageUrl = imageUrl
            )
        }
    }
}
