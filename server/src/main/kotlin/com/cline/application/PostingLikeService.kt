package com.cline.application

import com.cline.api.dto.PostingSimpleResponse
import com.cline.domain.PostingLike
import com.cline.repository.PostingLikeRepository
import com.cline.repository.PostingRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

interface PostingLikeService {

    fun like(userId: Long, postingId: Long)
    fun unlike(userId: Long, postingId: Long)
    fun isLike(userId: Long, postingId: Long): Boolean
    fun getLikePostings(userId: Long): List<PostingSimpleResponse>

    @Component
    @Transactional
    class Default(
            private val postingRepository: PostingRepository,
            private val userService: UserService,
            private val postingLikeRepository: PostingLikeRepository
    ) : PostingLikeService {

        override fun like(userId: Long, postingId: Long) {
            val user = findUser(userId)
            val posting = findPosting(postingId)
            if (postingLikeRepository.findTopByUserAndPosting(user, posting) != null) {
                return
            }
            postingLikeRepository.save(PostingLike(user = user, posting = posting))
        }

        override fun unlike(userId: Long, postingId: Long) {
            val user = findUser(userId)
            val posting = findPosting(postingId)
            postingLikeRepository.findTopByUserAndPosting(user, posting)?.run {
                postingLikeRepository.deleteById(id)
            }
        }

        override fun isLike(userId: Long, postingId: Long): Boolean {
            val user = findUser(userId)
            val posting = findPosting(postingId)
            return postingLikeRepository.findTopByUserAndPosting(user, posting) != null
        }

        override fun getLikePostings(userId: Long): List<PostingSimpleResponse> {
            val user = findUser(userId)
            return postingLikeRepository.findAllByUserOrderByIdDesc(user).map {
                val likeCount = it.posting.likes.size.toLong()
                PostingSimpleResponse.from(it.posting, likeCount)
            }
        }

        private fun findUser(userId: Long) = userService.findById(userId)

        private fun findPosting(postingId: Long) = (postingRepository.findById(postingId).getOrNull()
                ?: throw IllegalArgumentException("포스팅이 없습니다. | ${postingId}"))
    }
}
