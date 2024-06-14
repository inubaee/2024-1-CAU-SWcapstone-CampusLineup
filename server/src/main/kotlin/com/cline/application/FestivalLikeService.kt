package com.cline.application

import com.cline.domain.Festival
import com.cline.domain.FestivalLike
import com.cline.repository.FestivalLikeRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

interface FestivalLikeService {

    fun like(userId: Long, festivalId: Long)
    fun unlike(userId: Long, festivalId: Long)
    fun isLike(userId: Long, festivalId: Long): Boolean
    fun getLikeFestivals(userId: Long): List<Festival>

    @Component
    @Transactional
    class Default(
            private val festivalService: FestivalService,
            private val userService: UserService,
            private val festivalLikeRepository: FestivalLikeRepository
    ) : FestivalLikeService {

        override fun like(userId: Long, festivalId: Long) {
            val user = findUser(userId)
            val festival = findFestival(festivalId)
            if (festivalLikeRepository.findTopByUserAndFestival(user, festival) != null) {
                return
            }
            festivalLikeRepository.save(FestivalLike(user = user, festival = festival))
        }

        override fun unlike(userId: Long, festivalId: Long) {
            val user = findUser(userId)
            val festival = findFestival(festivalId)
            festivalLikeRepository.findTopByUserAndFestival(user, festival)?.let {
                festivalLikeRepository.deleteById(it.id)
            }
        }

        override fun isLike(userId: Long, festivalId: Long): Boolean {
            val user = findUser(userId)
            val festival = findFestival(festivalId)
            return festivalLikeRepository.findTopByUserAndFestival(user, festival) != null
        }

        override fun getLikeFestivals(userId: Long): List<Festival> {
            val user = findUser(userId)
            return festivalLikeRepository.findAllByUserOrderByIdDesc(user).map { it.festival }
        }

        private fun findUser(userId: Long) = userService.findById(userId)

        private fun findFestival(festivalId: Long) = festivalService.findById(festivalId)
    }
}
