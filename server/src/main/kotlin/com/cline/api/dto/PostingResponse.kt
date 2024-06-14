package com.cline.api.dto

import com.cline.domain.Posting
import java.time.LocalDateTime

data class PostingResponse(
        val id: Long,
        val writer: UserResponse,
        val title: String,
        val description: String,
        var vieWCount: Long,
        val likeCount: Long,
        val category: String,
        val comments: List<PostingCommentResponse>,
        val createdAt: LocalDateTime
) {

    companion object {

        fun with(posting: Posting, likeCount: Long) = with(posting) {
            PostingResponse(
                    id = id,
                    writer = UserResponse.from(user = writer),
                    title = title,
                    description = description,
                    vieWCount = vieWCount,
                    category = category.title,
                    comments = comments.map { PostingCommentResponse.from(it) },
                    createdAt = createdAt,
                    likeCount = likeCount
            )
        }
    }
}
