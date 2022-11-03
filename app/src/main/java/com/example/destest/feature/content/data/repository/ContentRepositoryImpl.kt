package com.example.destest.feature.content.data.repository

import com.example.destest.core.ErrorMessage
import com.example.destest.core.util.Resource
import com.example.destest.feature.content.data.local.StoryDao
import com.example.destest.feature.content.data.local.VideoDao
import com.example.destest.feature.content.data.remote.ContentApi
import com.example.destest.feature.content.data.remote.dto.ContentDto
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

        val contents = getContentLocal(storyDao, videoDao)
        emit(Resource.Loading(getContentLocal(storyDao, videoDao)))

        try {
            val remoteContent = api.getContent()
            updateContentLocal(storyDao, videoDao, remoteContent)
            emit(Resource.Success(getContentLocal(storyDao, videoDao)))
        } catch (e: HttpException) {
            emit(Resource.Error(ErrorMessage.HTTP_EXCEPTION.message, contents))
        } catch (e: IOException) {
            emit(Resource.Error(ErrorMessage.IO_EXCEPTION.message, contents))
        }
    }

    private suspend fun updateContentLocal(
        storyDao: StoryDao,
        videoDao: VideoDao,
        remoteContent: ContentDto
    ) {
        storyDao.deleteStories(remoteContent.stories.map { it.id })
        storyDao.insertStories(remoteContent.stories.map { it.toStoryEntity() })
        videoDao.deleteVideos(remoteContent.videos.map { it.id })
        videoDao.insertVideos(remoteContent.videos.map { it.toVideoEntity() })
    }

    private suspend fun getContentLocal(storyDao: StoryDao, videoDao: VideoDao): List<ContentItem> {
        val stories = storyDao.getStories().map { it.toStory() }.sortedByDescending { it.date }
        val videos = videoDao.getVideos().map { it.toVideo() }.sortedByDescending { it.date }
        return shuffleContent(videos, stories)
    }

    private fun shuffleContent(
        list1: List<ContentItem>,
        list2: List<ContentItem>
    ): List<ContentItem> {
        return when {
            list1.size > list2.size -> shuffleContentSized(list1, list2)
            list2.size > list1.size -> shuffleContentSized(list2, list1)
            else -> shuffleContentEqual(list1, list2)
        }
    }

    private fun shuffleContentSized(listBig: List<ContentItem>, listSmall: List<ContentItem>) =
        listBig.zip(listSmall)
            .flatMap { listOf(it.first, it.second) } + listBig.drop(listSmall.size)

    private fun shuffleContentEqual(list1: List<ContentItem>, list2: List<ContentItem>) =
        list1.zip(list2).flatMap { listOf(it.first, it.second) }
}
