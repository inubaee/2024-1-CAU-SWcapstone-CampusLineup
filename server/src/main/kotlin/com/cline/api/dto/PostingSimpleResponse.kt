package com.cline.api.dto

import com.cline.domain.Posting
import java.time.LocalDateTime

data class PostingSimpleResponse(
        val id: Long,
        val writer: UserResponse,
        val title: String,
        val description: String,
        var vieWCount: Long,
        val likeCount: Long,
        val category: String,
        val createdAt: LocalDateTime
) {

    companion object {

        fun from(posting: Posting, likeCount: Long) = with(posting) {
            PostingSimpleResponse(
                    id = id,
                    writer = UserResponse.from(user = writer),
                    title = title,
                    description = description,
                    vieWCount = vieWCount,
                    category = category.title,
                    createdAt = createdAt,
                    likeCount = likeCount
            )
        }
    }
}
