package com.example.destest.feature.story.presentation

import com.example.destest.feature.content.domain.model.Story

data class StoryState(
    val story: Story = Story.Empty,
    val isLoading: Boolean = false
)
