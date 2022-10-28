package com.example.destest.feature.content.domain.repository

import com.example.destest.core.util.Resource
import com.example.destest.feature.content.domain.model.Video
import kotlinx.coroutines.flow.Flow

interface VideoRepository {

    fun getVideos(): Flow<Resource<List<Video>>>

    fun getVideo(id: Int): Flow<Resource<Video>>
}
