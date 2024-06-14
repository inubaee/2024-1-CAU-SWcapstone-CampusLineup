package com.cline.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class ControllerAdvice {


    @ExceptionHandler(value = [IllegalArgumentException::class])
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<Map<String, String>> {
        val responseBody = mapOf("message" to (e.message ?: "예외 발생"))
        return ResponseEntity(responseBody, HttpStatus.BAD_REQUEST)
    }
}
