package com.cline.api.dto

import com.cline.domain.School

data class SchoolResponse(
        val id: Long,
        val name: String,
        val location: String,
        val instagram: String?
) {

    companion object {

        fun from(school: School) = with(school) {
            SchoolResponse(
                    id = id,
                    name = name,
                    location = location.name,
                    instagram = instagram
            )
        }
    }
}
