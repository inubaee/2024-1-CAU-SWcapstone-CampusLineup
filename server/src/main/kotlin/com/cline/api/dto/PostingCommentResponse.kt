package com.cline.api.dto

import com.cline.domain.PostingComment
import java.time.LocalDateTime

data class PostingCommentResponse(
        val id: Long,
        val writer: UserResponse,
        val value: String,
        val createdAt: LocalDateTime
) {

    companion object {

        fun from(comment: PostingComment) = with(comment) {
            PostingCommentResponse(
                    id = id,
                    writer = UserResponse.from(user = writer),
                    value = value,
                    createdAt = createdAt
            )
        }
    }
}
