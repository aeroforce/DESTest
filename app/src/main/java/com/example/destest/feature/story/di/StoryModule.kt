package com.example.destest.feature.story.di

import com.example.destest.feature.content.data.local.StoryDatabase
import com.example.destest.feature.content.data.remote.ContentApi
import com.example.destest.feature.story.data.repository.StoryRepositoryImpl
import com.example.destest.feature.story.domain.repository.StoryRepository
import com.example.destest.feature.story.domain.usecase.GetStory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StoryModule {
    @Provides
    @Singleton
    fun provideGetStoryUseCase(repository: StoryRepository): GetStory {
        return GetStory(repository)
    }

    @Provides
    @Singleton
    fun provideStoryRepository(
        db: StoryDatabase,
        api: ContentApi,
    ): StoryRepository {
        return StoryRepositoryImpl(api, db.dao)
    }
}
