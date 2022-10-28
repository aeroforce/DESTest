package com.example.destest.feature.content.data.remote.dto

import com.example.destest.feature.content.data.local.entity.VideoEntity
import com.example.destest.feature.content.domain.model.Video

data class VideoDto(
    val date: Double,
    val id: Int,
    val sport: SportDto,
    val thumb: String,
    val title: String,
    val url: String,
    val views: Int
) {
    fun toVideo(): Video = Video(
        date = date,
        id = id,
        sport = sport.name,
        thumb = thumb,
        title = title,
        url = url,
        views = views,
    )

    fun toVideoEntity() = VideoEntity(
        date = date,
        id = id,
        sport = sport.name,
        thumb = thumb,
        title = title,
        url = url,
        views = views,
    )
}
