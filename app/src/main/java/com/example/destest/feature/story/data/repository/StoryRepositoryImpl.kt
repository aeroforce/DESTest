package com.example.destest.feature.story.data.repository

import com.example.destest.core.ErrorMessage
import com.example.destest.core.util.Resource
import com.example.destest.feature.content.data.local.StoryDao
import com.example.destest.feature.content.data.remote.ContentApi
import com.example.destest.feature.content.domain.model.Story
import com.example.destest.feature.story.domain.repository.StoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class StoryRepositoryImpl(
    private val api: ContentApi,
    private val dao: StoryDao,
) : StoryRepository {

    override fun getStory(id: Int): Flow<Resource<Story>> = flow {
        emit(Resource.Loading())

        val story = dao.getStory(id).toStory()
        emit(Resource.Loading(data = story))

        try {
            val remoteStory = api.getContent().stories.find { it.id == id }
            if (remoteStory != null) {
                dao.deleteStories(listOf(remoteStory.id))
                dao.insertStories(listOf(remoteStory.toStoryEntity()))
            }
            val newStory = dao.getStory(id).toStory()
            emit(Resource.Success(newStory))
        } catch (e: HttpException) {
            emit(Resource.Error(ErrorMessage.HTTP_EXCEPTION.message, story))
        } catch (e: IOException) {
            emit(Resource.Error(ErrorMessage.IO_EXCEPTION.message, story))
        }
    }
}
