package com.cline.domain

import jakarta.persistence.*

@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,
        var name: String = "홍길동",
        @ManyToOne(fetch = FetchType.LAZY)
        val school: School? = null
) {

    fun leave() {
        this.name = "탈퇴한 회원"
    }
}
