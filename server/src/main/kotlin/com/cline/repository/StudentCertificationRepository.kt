package com.cline.repository

import com.cline.domain.StudentCertification
import org.springframework.data.jpa.repository.JpaRepository

interface StudentCertificationRepository : JpaRepository<StudentCertification, Long> {

    fun findTopByCode(code: String): StudentCertification?
}
