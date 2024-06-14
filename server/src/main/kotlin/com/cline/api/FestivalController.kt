package com.cline.api

import com.cline.api.dto.FestivalDetailResponse
import com.cline.api.dto.FestivalSimpleResponse
import com.cline.application.FestivalSearchService
import com.cline.application.FestivalService
import com.cline.domain.Location
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/festivals")
class FestivalController(
        private val festivalService: FestivalService,
        private val festivalSearchService: FestivalSearchService
) {

    @Operation(summary = "id로 축제 정보 상세 조회")
    @GetMapping("/{id}")
    fun getFestivalById(
            @PathVariable id: Long,
            @RequestParam(required = false) increaseViewCount: Boolean = true
    ): FestivalDetailResponse {
        val festival = when (increaseViewCount) {
            true -> festivalService.findByIdWithCounting(id)
            false -> festivalService.findById(id)
        }
        return FestivalDetailResponse.from(festival)
    }

    @Operation(summary = "인기 축제 정보 조회")
    @GetMapping("/popular")
    fun getPopularFestivals(): List<FestivalSimpleResponse> {
        return festivalService.findPopularFestivals().map { FestivalSimpleResponse.from(it) }
    }

    @Operation(summary = "축제 검색")
    @GetMapping("/search")
    fun searchFestivals(
            @RequestParam(required = false) keyword: String?,
            @RequestParam(required = false) location: Location?,
            @RequestParam(required = false) startDate: LocalDate?,
            @RequestParam(required = false) endDate: LocalDate?,
    ): List<FestivalSimpleResponse> {
        return festivalSearchService.search(keyword, location, startDate, endDate).map { FestivalSimpleResponse.from(it) }
    }
}
