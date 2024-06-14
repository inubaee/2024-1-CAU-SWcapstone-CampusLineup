package com.cline.repository

import com.cline.domain.Posting
import com.cline.domain.PostingCategory
import org.springframework.data.jpa.repository.JpaRepository

interface PostingRepository : JpaRepository<Posting, Long> {

    fun findAllByCategory(category: PostingCategory): List<Posting>
}
