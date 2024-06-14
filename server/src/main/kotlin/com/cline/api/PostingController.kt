package com.cline.api

import com.cline.api.dto.PostingCommentRequest
import com.cline.api.dto.PostingCommentResponse
import com.cline.api.dto.PostingRequest
import com.cline.application.PostingService
import com.cline.domain.PostingCategory
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posting")
class PostingController(
        private val postingService: PostingService
) {

    @Operation(summary = "게시글 등록")
    @PostMapping
    fun savePost(
            @RequestBody request: PostingRequest
    ) = postingService.post(request)

    @Operation(summary = "게시글 목록 조회")
    @GetMapping
    fun findAll(
            @RequestParam(required = false) category: PostingCategory = PostingCategory.전체,
            @RequestParam(required = false) userId: Long?
    ) = postingService.findAll(category, userId)

    @Operation(summary = "게시글 상세 조회")
    @GetMapping("/{id}")
    fun findAllById(
            @PathVariable id: Long,
            @RequestParam(required = false) increaseViewCount: Boolean = true
    ) = postingService.findById(id, increaseViewCount)

    @Operation(summary = "댓글 작성")
    @PostMapping("/comments")
    fun writeComment(
            @RequestBody request: PostingCommentRequest
    ) = PostingCommentResponse.from(postingService.writeComment(request))

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/comments/{id}")
    fun deleteComment(
            @PathVariable(value = "id") commentId: Long,
            @RequestParam userId: Long
    ) = postingService.deleteComment(userId, commentId)
}
