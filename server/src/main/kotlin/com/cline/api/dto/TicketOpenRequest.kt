package com.cline.api.dto

import java.time.LocalDateTime

data class TicketOpenRequest(
        val openUserId: Long,
        val schoolId: Long,
        val totalAmount: Long,
        val ticketOpenTime: LocalDateTime,
        val ticketCloseTime: LocalDateTime,
        val eventTime: LocalDateTime,
        val title: String,
        val location: String,
        val description: String,
)
