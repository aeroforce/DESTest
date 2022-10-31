package com.example.destest.feature.player.domain.usecase

import com.example.destest.core.util.Resource
import com.example.destest.feature.content.domain.model.Video
import com.example.destest.feature.player.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow

class GetVideo(private val repository: VideoRepository) {
    operator fun invoke(id: Int): Flow<Resource<Video>> = repository.getVideo(id)
}
