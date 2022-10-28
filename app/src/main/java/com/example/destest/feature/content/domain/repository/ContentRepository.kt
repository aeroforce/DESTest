package com.example.destest.feature.content.domain.repository

import com.example.destest.core.util.Resource
import com.example.destest.feature.content.domain.model.ContentItem
import kotlinx.coroutines.flow.Flow

interface ContentRepository {
    fun getContent(): Flow<Resource<List<ContentItem>>>
}
