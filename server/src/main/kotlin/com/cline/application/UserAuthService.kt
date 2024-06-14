package com.cline.application

import com.cline.api.dto.PasswordRequest
import com.cline.domain.StudentCertification
import com.cline.domain.User
import com.cline.domain.UserAuth
import com.cline.infra.GoogleMailClient
import com.cline.repository.SchoolRepository
import com.cline.repository.StudentCertificationRepository
import com.cline.repository.UserAuthRepository
import com.cline.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.ThreadLocalRandom
import java.util.stream.Collectors
import kotlin.jvm.optionals.getOrNull

@Service
@Transactional
class UserAuthService(
        private val userAuthRepository: UserAuthRepository,
        private val userRepository: UserRepository,
        private val schoolRepository: SchoolRepository,
        private val studentCertificationRepository: StudentCertificationRepository,
        private val googleMailClient: GoogleMailClient
) {

    fun login(id: String, password: String): User {
        val userAuth = userAuthRepository.findById(id).getOrNull()
                ?: throw IllegalArgumentException("존재하지 않는 id입니다.")
        if (userAuth.password != password) {
            throw IllegalArgumentException("비밀번호가 틀렸습니다.")
        }
        return userAuth.user
    }

    fun signUp(email: String, password: String, name: String, code: String): User {
        if (userAuthRepository.findById(email).getOrNull() != null) {
            throw IllegalArgumentException("중복된 id입니다.")
        }
        studentCertificationRepository.findTopByCode(code)
                ?.let {
                    if (it.email != email) {
                        throw IllegalArgumentException("인증정보가 올바르지 않습니다.")
                    }
                }
                ?: throw IllegalArgumentException("인증정보가 올바르지 않습니다.")

        val school = schoolRepository.findTopByDomain(getDomainFromEmail(email))
        val user = userRepository.save(User(name = name, school = school))
        userAuthRepository.save(UserAuth(
                id = email,
                password = password,
                user = user
        ))
        return user
    }

    fun sendMail(email: String) {
        if (isValidEmail(email).not()) {
            throw IllegalArgumentException("올바른 이메일 형식이 아닙니다.")
        }
        val certification = studentCertificationRepository.save(StudentCertification(code = createCode(), email = email))
        googleMailClient.send(certification)
    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        return email.matches(emailRegex.toRegex())
    }


    private fun createCode(): String {
        val codeLength = 6L
        val random = ThreadLocalRandom.current()
        return random.ints(codeLength, 0, 10)
                .mapToObj { i: Int -> java.lang.String.valueOf(i) }
                .collect(Collectors.joining())
    }

    private fun getDomainFromEmail(email: String): String {
        val parts = email.split("@")
        return if (parts.size == 2) {
            parts[1]
        } else {
            ""
        }
    }

    fun canUseId(id: String): Boolean {
        return userAuthRepository.findById(id).isEmpty
    }

    fun leave(userId: Long) {
        val user = userRepository.findById(userId).getOrNull() ?: return
        user.leave()
        userAuthRepository.deleteByUser(user)
    }

    fun changePassword(userId: Long, request: PasswordRequest) {
        val user = userRepository.findById(userId).getOrNull() ?: return
        val userAuth = userAuthRepository.findTopByUser(user) ?: return
        if (userAuth.password != request.oldPassword) {
            throw IllegalArgumentException("비밀번호가 일치하지 않습니다.")
        }
        userAuth.password = request.newPassword
    }
}
