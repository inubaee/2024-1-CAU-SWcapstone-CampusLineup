package com.cline.repository

import com.cline.domain.Posting
import com.cline.domain.PostingLike
import com.cline.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface PostingLikeRepository : JpaRepository<PostingLike, Long> {

    fun findTopByUserAndPosting(user: User, posting: Posting): PostingLike?
    fun findAllByUserOrderByIdDesc(user: User): List<PostingLike>
    fun countAllByPosting(posting: Posting): Long
}
