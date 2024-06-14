package com.cline.api

import com.cline.api.dto.PostingSimpleResponse
import com.cline.application.PostingLikeService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/like/postings")
class PostingLikeController(
        private val postingLikeService: PostingLikeService
) {

    @Operation(summary = "게시글 좋아요")
    @PostMapping("/{id}")
    fun likeFestival(
            @PathVariable id: Long,
            @RequestParam userId: Long
    ) {
        postingLikeService.like(userId = userId, postingId = id)
    }

    @Operation(summary = "게시글 좋아요 취소")
    @DeleteMapping("/{id}")
    fun unlikeFestival(
            @PathVariable id: Long,
            @RequestParam userId: Long
    ) {
        postingLikeService.unlike(userId = userId, postingId = id)
    }

    @Operation(summary = "게시글 좋아요 여부 확인")
    @GetMapping("/{id}")
    fun isLikeFestival(
            @PathVariable id: Long,
            @RequestParam userId: Long
    ): Boolean {
        return postingLikeService.isLike(userId = userId, postingId = id)
    }

    @Operation(summary = "좋아요한 게시글 리스트 조회")
    @GetMapping
    fun getLikeFestivals(
            @RequestParam userId: Long
    ): List<PostingSimpleResponse> {
        return postingLikeService.getLikePostings(userId)
    }
}
