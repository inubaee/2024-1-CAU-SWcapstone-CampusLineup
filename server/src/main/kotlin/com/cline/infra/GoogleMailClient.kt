package com.cline.infra

import com.cline.domain.StudentCertification
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service


@Service
class GoogleMailClient(
        private val mailSender: MailSender,
        @Value("\${spring.mail.username}") private val fromMail: String) {

    @Async
    fun send(payload: StudentCertification) {
        val mail = SimpleMailMessage()
        mail.from = fromMail
        mail.setTo(payload.email)
        mail.subject = "[캠퍼스 라인업] 인증 코드"
        mail.text = payload.code
        mailSender.send(mail)
    }
}
