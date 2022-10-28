package com.example.destest.feature.content.domain.usecase

import com.example.destest.core.util.Resource
import com.example.destest.feature.content.domain.model.Video
import com.example.destest.feature.content.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow

class GetVideos(private val repository: VideoRepository) {
    operator fun invoke(): Flow<Resource<List<Video>>> = repository.getVideos()
}
