package com.cline.repository

import com.cline.domain.PostingComment
import org.springframework.data.jpa.repository.JpaRepository

interface PostingCommentRepository : JpaRepository<PostingComment, Long> {
}
