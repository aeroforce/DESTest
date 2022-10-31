package com.example.destest.feature.story.data.repository

import android.util.Log
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
    override fun getStories(): Flow<Resource<List<Story>>> = flow {
        emit(Resource.Loading())

        val stories = dao.getStories().map { it.toStory() }
        emit(Resource.Loading(data = stories))

        try {
            val remoteStories = api.getContent().stories
            dao.deleteStories(remoteStories.map { it.id })
            dao.insertStories(remoteStories.map { it.toStoryEntity() })
        } catch (e: HttpException) {
            emit(Resource.Error("Http error", stories))
        } catch (e: IOException) {
            emit(Resource.Error("Connection problem error", stories))
        }

        val newStories = dao.getStories().map { it.toStory() }
        emit(Resource.Success(newStories))
    }

    override fun getStory(id: Int): Flow<Resource<Story>> = flow {
        emit(Resource.Loading())

        Log.d("AERODEBUG", "getStory in StoryRepositoryImpl: getStory for id: $id")
        val story = dao.getStory(id).toStory()
        emit(Resource.Loading(data = story))

        try {
            val remoteStory = api.getContent().stories.find { it.id == id }
            if (remoteStory != null) {
                dao.deleteStories(listOf(remoteStory.id))
                dao.insertStories(listOf(remoteStory.toStoryEntity()))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Http error", story))
        } catch (e: IOException) {
            emit(Resource.Error("Connection problem error", story))
        }

        val newStory = dao.getStory(id).toStory()
        emit(Resource.Success(newStory))
    }
}
