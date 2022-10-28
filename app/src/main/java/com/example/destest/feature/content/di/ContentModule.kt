package com.example.destest.feature.content.di

import android.app.Application
import androidx.room.Room
import com.example.destest.feature.content.data.local.StoryDatabase
import com.example.destest.feature.content.data.local.VideoDatabase
import com.example.destest.feature.content.data.remote.ContentApi
import com.example.destest.feature.content.data.repository.ContentRepositoryImpl
import com.example.destest.feature.content.data.repository.StoryRepositoryImpl
import com.example.destest.feature.content.data.repository.VideoRepositoryImpl
import com.example.destest.feature.content.domain.repository.ContentRepository
import com.example.destest.feature.content.domain.repository.StoryRepository
import com.example.destest.feature.content.domain.repository.VideoRepository
import com.example.destest.feature.content.domain.usecase.GetContent
import com.example.destest.feature.content.domain.usecase.GetStories
import com.example.destest.feature.content.domain.usecase.GetStory
import com.example.destest.feature.content.domain.usecase.GetVideos
import com.example.destest.feature.player.domain.usecase.GetVideo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ContentModule {

    @Provides
    @Singleton
    fun provideGetStoriesUseCase(repository: StoryRepository): GetStories {
        return GetStories(repository)
    }

    @Provides
    @Singleton
    fun provideGetVideosUseCase(repository: VideoRepository): GetVideos {
        return GetVideos(repository)
    }

    @Provides
    @Singleton
    fun provideGetContentUseCase(repository: ContentRepository): GetContent {
        return GetContent(repository)
    }

    @Provides
    @Singleton
    fun provideGetStoryUseCase(repository: StoryRepository): GetStory {
        return GetStory(repository)
    }

    @Provides
    @Singleton
    fun provideGetVideoUseCase(repository: VideoRepository): GetVideo {
        return GetVideo(repository)
    }

    @Provides
    @Singleton
    fun provideStoryRepository(
        db: StoryDatabase,
        api: ContentApi,
    ): StoryRepository {
        return StoryRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideVideoRepository(
        db: VideoDatabase,
        api: ContentApi,
    ): VideoRepository {
        return VideoRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideContentRepository(
        dbStory: StoryDatabase,
        dbVideo: VideoDatabase,
        api: ContentApi,
    ): ContentRepository {
        return ContentRepositoryImpl(api, dbStory.dao, dbVideo.dao)
    }

    @Provides
    @Singleton
    fun provideStoryDatabase(app: Application): StoryDatabase {
        return Room.databaseBuilder(
            app,
            StoryDatabase::class.java,
            "story_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideVideoDatabase(app: Application): VideoDatabase {
        return Room.databaseBuilder(
            app,
            VideoDatabase::class.java,
            "video_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideContentApi(): ContentApi {
        return Retrofit.Builder()
            .baseUrl(ContentApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ContentApi::class.java)
    }
}
