package com.cline.repository

import com.cline.domain.Festival
import com.cline.domain.FestivalLike
import com.cline.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface FestivalLikeRepository : JpaRepository<FestivalLike, Long> {

    fun findTopByUserAndFestival(user: User, festival: Festival): FestivalLike?
    fun findAllByUserOrderByIdDesc(user: User): List<FestivalLike>
}
