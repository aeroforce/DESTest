package com.example.destest.feature.content.data.repository

import com.example.destest.core.ErrorMessage
import com.example.destest.core.util.Resource
import com.example.destest.feature.content.data.local.StoryDao
import com.example.destest.feature.content.data.local.VideoDao
import com.example.destest.feature.content.data.remote.ContentApi
import com.example.destest.feature.content.domain.model.ContentItem
import com.example.destest.feature.content.domain.repository.ContentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ContentRepositoryImpl(
    private val api: ContentApi,
    private val storyDao: StoryDao,
    private val videoDao: VideoDao,
) : ContentRepository {
    override fun getContent(): Flow<Resource<List<ContentItem>>> = flow {
        emit(Resource.Loading())

        val stories = storyDao.getStories().map { it.toStory() }
        val videos = videoDao.getVideos().map { it.toVideo() }

        val contents = (stories + videos).sortedByDescending { it.date }
        emit(Resource.Loading(data = contents))

        try {
            val remoteContent = api.getContent()

            storyDao.deleteStories(remoteContent.stories.map { it.id })
            storyDao.insertStories(remoteContent.stories.map { it.toStoryEntity() })
            videoDao.deleteVideos(remoteContent.videos.map { it.id })
            videoDao.insertVideos(remoteContent.videos.map { it.toVideoEntity() })
        } catch (e: HttpException) {
            emit(Resource.Error(ErrorMessage.HTTP_EXCEPTION.message, contents))
        } catch (e: IOException) {
            emit(Resource.Error(ErrorMessage.IO_EXCEPTION.message, contents))
        }

        val newStories = storyDao.getStories().map { it.toStory() }
        val newVideos = videoDao.getVideos().map { it.toVideo() }
        val newContents = (newStories + newVideos).sortedByDescending { it.date }
        emit(Resource.Success(newContents))
    }
}
