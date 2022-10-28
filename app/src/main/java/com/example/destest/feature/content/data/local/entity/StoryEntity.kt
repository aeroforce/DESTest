package com.example.destest.feature.content.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.destest.feature.content.domain.model.Story

@Entity
data class StoryEntity(
    @PrimaryKey val id: Int? = null,
    val author: String,
    val date: Double,
    val image: String,
    val sport: String,
    val teaser: String,
    val title: String
) {
    fun toStory() = Story(
        id = id ?: 0,
        author = author,
        date = date,
        image = image,
        sport = sport,
        teaser = teaser,
        title = title,
    )
}
