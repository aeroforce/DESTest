package com.example.destest.feature.content.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.destest.feature.content.domain.model.Video

@Entity
data class VideoEntity(
    @PrimaryKey val id: Int? = null,
    val date: Double,
    val sport: String,
    val thumb: String,
    val title: String,
    val url: String,
    val views: Int,
) {
    fun toVideo() = Video(
        id = id ?: 0,
        sport = sport,
        date = date,
        thumb = thumb,
        url = url,
        title = title,
        views = views,
    )
}
