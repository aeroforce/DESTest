package com.example.destest.feature.content.data.remote.dto

import com.example.destest.feature.content.data.local.entity.StoryEntity
import com.example.destest.feature.content.domain.model.Story

data class StoryDto(
    val author: String,
    val date: Double,
    val id: Int,
    val image: String,
    val sport: SportDto,
    val teaser: String,
    val title: String
) {
    fun toStory() = Story(
        author = author,
        date = date,
        id = id,
        image = image,
        sport = sport.name,
        teaser = teaser,
        title = title,
    )

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
