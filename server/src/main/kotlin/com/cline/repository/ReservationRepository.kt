package com.cline.repository

import com.cline.domain.Reservation
import com.cline.domain.Ticketing
import com.cline.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface ReservationRepository : JpaRepository<Reservation, Long> {

    fun findAllByUser(user: User): List<Reservation>
    fun findTopByUserAndTicketing(user: User, ticketing: Ticketing): Reservation?
}
