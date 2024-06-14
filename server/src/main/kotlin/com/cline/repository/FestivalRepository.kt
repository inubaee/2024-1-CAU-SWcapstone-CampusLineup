package com.cline.repository

import com.cline.domain.Festival
import com.cline.domain.Season
import org.springframework.data.jpa.repository.JpaRepository

interface FestivalRepository : JpaRepository<Festival, Long> {

    fun findAllBySeasonOrderByViewCountDesc(season: Season): List<Festival>
}
