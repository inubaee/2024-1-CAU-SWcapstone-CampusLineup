package com.cline.repository

import com.cline.domain.Ticketing
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import java.util.*

interface TicketingRepository : JpaRepository<Ticketing, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select t from Ticketing t where t.id = :id")
    fun findByIdForUpdate(id: Long): Optional<Ticketing>
}
