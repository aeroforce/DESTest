package com.example.destest.feature.content.data.repository

import com.example.destest.core.util.Resource
import com.example.destest.feature.content.data.local.VideoDao
import com.example.destest.feature.content.data.remote.ContentApi
import com.example.destest.feature.content.domain.model.Video
import com.example.destest.feature.content.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class VideoRepositoryImpl(
    private val api: ContentApi,
    private val dao: VideoDao,
) : VideoRepository {
    override fun getVideos(): Flow<Resource<List<Video>>> = flow {
        emit(Resource.Loading())

        val videos = dao.getVideos().map { it.toVideo() }
        emit(Resource.Loading(data = videos))

        try {
            val remoteVideos = api.getContent().videos
            dao.deleteVideos(remoteVideos.map { it.id })
            dao.insertVideos(remoteVideos.map { it.toVideoEntity() })
        } catch (e: HttpException) {
            emit(Resource.Error("Http error", videos))
        } catch (e: IOException) {
            emit(Resource.Error("Connection problem error", videos))
        }

        val newVideos = dao.getVideos().map { it.toVideo() }
        emit(Resource.Success(newVideos))
    }

    override fun getVideo(id: Int): Flow<Resource<Video>> = flow {
        emit(Resource.Loading())

        val story = dao.getVideo(id).toVideo()
        emit(Resource.Loading(data = story))

        try {
            val remoteVideo = api.getContent().videos.find { it.id == id }
            if (remoteVideo != null) {
                dao.deleteVideos(listOf(remoteVideo.id))
                dao.insertVideos(listOf(remoteVideo.toVideoEntity()))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Http error", story))
        } catch (e: IOException) {
            emit(Resource.Error("Connection problem error", story))
        }

        val newStory = dao.getVideo(id).toVideo()
        emit(Resource.Success(newStory))
    }
}
