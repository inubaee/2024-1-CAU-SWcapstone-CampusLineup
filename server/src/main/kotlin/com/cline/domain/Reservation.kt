package com.cline.domain

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
data class Reservation(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,
        @ManyToOne(fetch = FetchType.LAZY)
        val ticketing: Ticketing,
        @ManyToOne(fetch = FetchType.LAZY)
        val user: User,
        val number: Long,
        @CreatedDate
        val createdAt: LocalDateTime = LocalDateTime.now()
) {

    companion object {
        fun of(user: User, ticketing: Ticketing) = Reservation(
                ticketing = ticketing,
                user = user,
                number = ticketing.getNumber()
        )
    }
}
