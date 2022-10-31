package com.example.destest.feature.story.domain.repository

import com.example.destest.core.util.Resource
import com.example.destest.feature.content.domain.model.Story
import kotlinx.coroutines.flow.Flow

interface StoryRepository {

    fun getStories(): Flow<Resource<List<Story>>>

    fun getStory(id: Int): Flow<Resource<Story>>
}
