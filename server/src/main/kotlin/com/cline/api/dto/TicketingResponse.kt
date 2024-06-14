//package com.cline.api.dto
//
//import com.cline.domain.Ticketing
//import java.time.LocalDateTime
//
//data class TicketingResponse(
//        val id: Long,
//        val festival: FestivalDateTimeResponse,
//        val totalAmount: Long,
//        val remainAmount: Long,
//        val ticketOpenTime: LocalDateTime,
//        val ticketCloseTime: LocalDateTime
//) {
//
//    companion object {
//
//        fun from(ticketing: Ticketing) = with(ticketing) {
//            TicketingResponse(
//                    id = id,
//                    festival = FestivalDateTimeResponse.from(festivalDateTime),
//                    totalAmount = totalAmount,
//                    remainAmount = remainAmount,
//                    ticketOpenTime = ticketOpenTime,
//                    ticketCloseTime = ticketCloseTime
//            )
//        }
//    }
//}
