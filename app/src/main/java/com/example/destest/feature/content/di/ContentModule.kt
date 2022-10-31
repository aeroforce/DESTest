package com.example.destest.feature.content.di

import android.app.Application
import androidx.room.Room
import com.example.destest.feature.content.data.local.StoryDatabase
import com.example.destest.feature.content.data.local.VideoDatabase
import com.example.destest.feature.content.data.remote.ContentApi
import com.example.destest.feature.content.data.repository.ContentRepositoryImpl
import com.example.destest.feature.content.domain.repository.ContentRepository
import com.example.destest.feature.content.domain.usecase.GetContent
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
    fun provideGetContentUseCase(repository: ContentRepository): GetContent {
        return GetContent(repository)
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
