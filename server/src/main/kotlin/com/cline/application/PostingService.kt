package com.cline.application

import com.cline.api.dto.PostingCommentRequest
import com.cline.api.dto.PostingRequest
import com.cline.api.dto.PostingResponse
import com.cline.api.dto.PostingSimpleResponse
import com.cline.domain.Posting
import com.cline.domain.PostingCategory
import com.cline.domain.PostingComment
import com.cline.domain.User
import com.cline.repository.PostingCommentRepository
import com.cline.repository.PostingRepository
import com.cline.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

@Service
@Transactional
class PostingService(
        private val postingRepository: PostingRepository,
        private val userRepository: UserRepository,
        private val postingCommentRepository: PostingCommentRepository
) {

    fun post(request: PostingRequest): Posting {
        val user = findUser(request.userId)

        val posting = with(request) {
            Posting(
                    writer = user,
                    title = title,
                    description = description,
                    category = category,
            )
        }
        return postingRepository.save(posting)
    }

    private fun findUser(userId: Long): User {
        return userRepository.findById(userId).getOrNull()
                ?: throw IllegalArgumentException("유저가 없습니다. | ${userId}")
    }

    fun findById(postingId: Long, withCounting: Boolean = true): PostingResponse {
        val posting = (postingRepository.findById(postingId).getOrNull()
                ?: throw IllegalArgumentException("포스팅이 없습니다. | ${postingId}"))
        if (withCounting) {
            posting.vieWCount += 1
        }
        val likeCount = posting.likes.count().toLong()
        return PostingResponse.with(posting, likeCount)
    }

    fun findAll(category: PostingCategory, userId: Long?): List<PostingSimpleResponse> {
        var postings = if (category == PostingCategory.전체) {
            postingRepository.findAll()
        } else {
            postingRepository.findAllByCategory(category)
        }

        if (userId != null) {
            postings = postings.filter { it.writer.id == userId }
        }

        return postings.sortedByDescending { it.createdAt }.map {
            val likeCount = it.likes.count().toLong()
            PostingSimpleResponse.from(it, likeCount)
        }
    }

    fun writeComment(request: PostingCommentRequest): PostingComment {
        val user = findUser(request.userId)
        val posting = postingRepository.findById(request.postingId).getOrNull()
                ?: throw IllegalArgumentException("포스팅이 없습니다. | ${request.postingId}")
        return postingCommentRepository.save(
                PostingComment(
                        writer = user,
                        value = request.comment,
                        posting = posting
                )
        )
    }

    fun deleteComment(userId: Long, commentId: Long) {
        val comment = postingCommentRepository.findById(commentId).getOrNull() ?: return
        if (comment.writer.id != userId) {
            throw IllegalArgumentException("작성자의 댓글이 아닙니다 | userId: ${userId} | commentId: ${commentId}")
        }
        postingCommentRepository.deleteById(commentId)
    }
}
