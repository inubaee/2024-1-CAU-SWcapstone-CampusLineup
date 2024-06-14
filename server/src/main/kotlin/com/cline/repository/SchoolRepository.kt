package com.cline.repository

import com.cline.domain.School
import org.springframework.data.jpa.repository.JpaRepository

interface SchoolRepository : JpaRepository<School, Long> {

    fun findTopByDomain(domain: String): School?
}
