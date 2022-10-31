package com.example.destest.feature.content.domain.usecase

import com.example.destest.core.util.Resource
import com.example.destest.feature.content.domain.model.Story
import com.example.destest.feature.story.domain.repository.StoryRepository
import kotlinx.coroutines.flow.Flow

class GetStories(private val repository: StoryRepository) {
    operator fun invoke(): Flow<Resource<List<Story>>> = repository.getStories()
}
