package com.example.destest.feature.content.presentation

import com.example.destest.feature.content.domain.model.ContentItem
import com.example.destest.feature.content.domain.model.Story
import com.example.destest.feature.content.domain.model.Video

data class ContentState(
    val stories: List<Story> = emptyList(),
    val videos: List<Video> = emptyList(),
    val content: List<ContentItem> = emptyList(),
    val isLoading: Boolean = false
)
