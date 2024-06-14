package com.cline.domain

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
data class Posting(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,
        @ManyToOne(fetch = FetchType.LAZY)
        val writer: User,
        val title: String,
        val description: String,
        var vieWCount: Long = 0L,
        @Enumerated(value = EnumType.STRING)
        val category: PostingCategory,
        @OneToMany(mappedBy = "posting", fetch = FetchType.LAZY)
        val comments: List<PostingComment> = emptyList(),
        @CreatedDate
        val createdAt: LocalDateTime = LocalDateTime.now(),
        @OneToMany(mappedBy = "posting", fetch = FetchType.EAGER)
        val likes: List<PostingLike> = emptyList()
)

enum class PostingCategory(val title: String) {
    전체("전체"),
    공지사항("공지사항"),
    자유_게시판("자유 게시판"),
    동행_찾기("동행 찾기"),
    축제_후기("축제 후기")
}
