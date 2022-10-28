package com.example.destest.feature.content.domain.usecase

import com.example.destest.core.util.Resource
import com.example.destest.feature.content.domain.model.ContentItem
import com.example.destest.feature.content.domain.repository.ContentRepository
import kotlinx.coroutines.flow.Flow

class GetContent(private val repository: ContentRepository) {
    operator fun invoke(): Flow<Resource<List<ContentItem>>> = repository.getContent()
}
