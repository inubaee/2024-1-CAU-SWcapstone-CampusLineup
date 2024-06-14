package com.cline.api

import com.cline.api.dto.TicketOpenRequest
import com.cline.application.TicketingService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ticketing")
class TicketingController(
        private val ticketingService: TicketingService
) {

    @Operation(summary = "티켓팅 정보 조회 (예매 가능 시간이 끝난 티켓 제외)")
    @GetMapping
    fun findAll() = ticketingService.findAll()

    @Operation(summary = "티켓 오픈")
    @PostMapping
    fun openTicket(
            @RequestBody request: TicketOpenRequest
    ) = ticketingService.openTicket(request)

    @Operation(summary = "티켓 예매")
    @PostMapping("/{ticketingId}/reserve")
    fun reserve(
            @PathVariable ticketingId: Long,
            @RequestParam userId: Long
    ) = ticketingService.reserve(userId = userId, ticketingId = ticketingId)

    @Operation(summary = "예매 내역 조회")
    @GetMapping("/reserve")
    fun findAllReserve(
            @RequestParam userId: Long
    ) = ticketingService.findReserved(userId)

    @Operation(summary = "티켓팅 검색")
    @GetMapping("/search/{keyword}")
    fun search(
            @PathVariable keyword: String
    ) = ticketingService.search(keyword)
}
