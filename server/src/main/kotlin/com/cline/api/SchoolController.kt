package com.cline.api

import com.cline.api.dto.SchoolResponse
import com.cline.repository.SchoolRepository
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/schools")
class SchoolController(
        private val schoolRepository: SchoolRepository
) {
    
    @Operation(summary = "학교 목록 조회")
    @GetMapping
    fun findAll() = schoolRepository.findAll().map { SchoolResponse.from(it) }

}
