package com.example.destest.feature.content.data.remote.dto

import com.example.destest.feature.content.domain.model.Content

data class ContentDto(
    val stories: List<StoryDto>,
    val videos: List<VideoDto>
) {
    fun toContent() = Content(
        stories = stories.map { it.toStory() },
        videos = videos.map { it.toVideo() },
    )
}
