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

        val stories = storyDao.getStories().map { it.toStory() }.sortedByDescending { it.date }
        val videos = videoDao.getVideos().map { it.toVideo() }.sortedByDescending { it.date }

        val contents = shuffleContent(videos, stories)
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

        val newStories = storyDao.getStories().map { it.toStory() }.sortedByDescending { it.date }
        val newVideos = videoDao.getVideos().map { it.toVideo() }.sortedByDescending { it.date }
        val newContents = shuffleContent(newVideos, newStories)
        emit(Resource.Success(newContents))
    }

    private fun shuffleContent(list1: List<ContentItem>, list2: List<ContentItem>): List<ContentItem> {
        return when {
            list1.size > list2.size -> shuffleContentSized(list1, list2)
            list2.size > list1.size -> shuffleContentSized(list2, list1)
            else -> shuffleContentEqual(list1, list2)
        }
    }

    private fun shuffleContentSized(listBig: List<ContentItem>, listSmall: List<ContentItem>) =
        listBig.zip(listSmall).flatMap { listOf(it.first, it.second) } + listBig.drop(listSmall.size)

    //private fun shuffleContentEqualD(list1: List<ContentItem>, list2: List<ContentItem>) = list1.zip(list2) { a,b -> listOf(a,b) }.flatten()

    private fun shuffleContentEqual(list1: List<ContentItem>, list2: List<ContentItem>) = list1.zip(list2).flatMap { listOf(it.first, it.second) }
}
