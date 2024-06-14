package com.cline.api

import com.cline.api.dto.FestivalSimpleResponse
import com.cline.application.FestivalLikeService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/like/festivals")
class FestivalLikeController(
        private val festivalLikeService: FestivalLikeService
) {

    @Operation(summary = "축제 좋아요")
    @PostMapping("/{id}")
    fun likeFestival(
            @PathVariable(value = "id") festivalId: Long,
            @RequestParam userId: Long
    ) {
        festivalLikeService.like(userId = userId, festivalId = festivalId)
    }

    @Operation(summary = "축제 좋아요 취소")
    @DeleteMapping("/{id}")
    fun unlikeFestival(
            @PathVariable(value = "id") festivalId: Long,
            @RequestParam userId: Long
    ) {
        festivalLikeService.unlike(userId = userId, festivalId = festivalId)
    }

    @Operation(summary = "축제 좋아요 여부 확인")
    @GetMapping("/{id}")
    fun isLikeFestival(
            @PathVariable(value = "id") festivalId: Long,
            @RequestParam userId: Long
    ): Boolean {
        return festivalLikeService.isLike(userId = userId, festivalId = festivalId)
    }

    @Operation(summary = "좋아요한 축제 리스트 조회")
    @GetMapping
    fun getLikeFestivals(
            @RequestParam userId: Long
    ): List<FestivalSimpleResponse> {
        return festivalLikeService.getLikeFestivals(userId).map { FestivalSimpleResponse.from(it) }
    }
}
