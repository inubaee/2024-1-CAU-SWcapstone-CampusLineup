package com.cline.domain

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
data class Ticketing(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,
        @ManyToOne(fetch = FetchType.LAZY)
        val openUser: User,
        @ManyToOne(fetch = FetchType.LAZY)
        val school: School? = null,
        val totalAmount: Long,
        var remainAmount: Long = totalAmount,
        val ticketOpenTime: LocalDateTime,
        val ticketCloseTime: LocalDateTime,
        val eventTime: LocalDateTime,
        val title: String,
        val location: String,
        val description: String,
        @CreatedDate
        val createdAt: LocalDateTime = LocalDateTime.now()
//        @OneToOne
//        val festivalDateTime: FestivalDateTime,
//        @ManyToOne
//        val school: School = festivalDateTime.festival.school,
) {
    fun isTicketingTime(now: LocalDateTime) = now.isAfter(ticketOpenTime) && now.isBefore(ticketCloseTime)
    fun getNumber() = totalAmount - remainAmount
    fun reserve(user: User): Reservation {
        if (canReserve().not()) {
            throw IllegalArgumentException("예매 가능 수량이 없습니다")
        }
        this.remainAmount -= 1
        return Reservation(
                ticketing = this,
                user = user,
                number = getNumber()
        )
    }

    private fun canReserve() = remainAmount > 0
}
