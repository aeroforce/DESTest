package com.example.destest.feature.content.presentation

import com.example.destest.feature.content.domain.model.ContentItem

data class ContentState(
    val content: List<ContentItem> = emptyList(),
    val isLoading: Boolean = false
)
