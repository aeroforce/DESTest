package com.example.destest.feature.story.domain.usecase

import com.example.destest.core.util.Resource
import com.example.destest.feature.content.domain.model.Story
import com.example.destest.feature.story.domain.repository.StoryRepository
import kotlinx.coroutines.flow.Flow

class GetStory(private val repository: StoryRepository) {
    operator fun invoke(id: Int): Flow<Resource<Story>> = repository.getStory(id)
}
