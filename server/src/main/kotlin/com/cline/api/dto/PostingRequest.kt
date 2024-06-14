package com.cline.api.dto

import com.cline.domain.PostingCategory

data class PostingRequest(
        val userId: Long,
        val title: String,
        val description: String,
        val category: PostingCategory = PostingCategory.자유_게시판
)
