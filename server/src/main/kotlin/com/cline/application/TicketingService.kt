package com.cline.application

import com.cline.api.dto.TicketOpenRequest
import com.cline.domain.Reservation
import com.cline.domain.Ticketing
import com.cline.repository.ReservationRepository
import com.cline.repository.SchoolRepository
import com.cline.repository.TicketingRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrNull

@Service
@Transactional
class TicketingService(
        val ticketingRepository: TicketingRepository,
        val reservationRepository: ReservationRepository,
        val userService: UserService,
        val schoolRepository: SchoolRepository
) {

    fun findAll(): List<Ticketing> {
        return ticketingRepository.findAll()
                .filter { LocalDateTime.now().isBefore(it.ticketCloseTime) }
    }

    fun reserve(userId: Long, ticketingId: Long): Reservation {
        val user = userService.findById(userId)
        val ticketing = ticketingRepository.findByIdForUpdate(ticketingId)
                .orElseThrow { IllegalArgumentException("Ticketing NotFound | ticketingId: $ticketingId") }
        if (ticketing.isTicketingTime(LocalDateTime.now()).not()) {
            throw IllegalArgumentException("예매 가능 시간이 아닙니다")
        }
        reservationRepository.findTopByUserAndTicketing(user, ticketing)?.run {
            throw IllegalArgumentException("이미 예매된 티켓입니다.")
        }
        if (user.school?.id != ticketing.school?.id) {
            throw IllegalArgumentException("해당 학교에 재학중인 학생이 아닙니다.")
        }
        val reservation = ticketing.reserve(user)
        return reservationRepository.save(reservation)
    }

    fun openTicket(request: TicketOpenRequest): Ticketing {
        val user = userService.findById(request.openUserId)
        if (user.school?.id != request.schoolId) {
            throw IllegalArgumentException("해당 학교에 재학중인 사용자가 아닙니다.")
        }
        val school = schoolRepository.findById(request.schoolId).getOrNull()
        val ticketing = with(request) {
            Ticketing(
                    openUser = user,
                    school = school,
                    totalAmount = totalAmount,
                    ticketOpenTime = ticketOpenTime,
                    ticketCloseTime = ticketCloseTime,
                    eventTime = eventTime,
                    title = title,
                    location = location,
                    description = description
            )
        }
        return ticketingRepository.save(ticketing)
    }

    fun findReserved(userId: Long): List<Reservation> {
        val user = userService.findById(userId)
        return reservationRepository.findAllByUser(user)
    }

    fun search(keyword: String): List<Ticketing> {
        return ticketingRepository.findAll().filter {
            it.school?.name?.contains(keyword, ignoreCase = true) ?: false ||
                    it.location.contains(keyword, ignoreCase = true) ||
                    it.title.contains(keyword, ignoreCase = true) ||
                    it.description.contains(keyword, ignoreCase = true)
        }
    }
}
