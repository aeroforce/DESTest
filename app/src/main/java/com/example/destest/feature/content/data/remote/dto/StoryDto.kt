package com.example.destest.feature.content.data.remote.dto

import com.example.destest.feature.content.data.local.entity.StoryEntity

data class StoryDto(
    val author: String,
    val date: Double,
    val id: Int,
    val image: String,
    val sport: SportDto,
    val teaser: String,
    val title: String
) {
    fun toStoryEntity() = StoryEntity(
        author = author,
        date = date,
        id = id,
        image = image,
        sport = sport.name,
        teaser = teaser,
        title = title,
    )
}
