package com.cline.api.dto

data class PostingCommentRequest(
        val userId: Long,
        val postingId: Long,
        val comment: String
)
