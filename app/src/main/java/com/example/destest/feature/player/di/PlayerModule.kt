package com.example.destest.feature.player.di

import com.example.destest.feature.content.data.local.VideoDatabase
import com.example.destest.feature.content.data.remote.ContentApi
import com.example.destest.feature.player.data.repository.VideoRepositoryImpl
import com.example.destest.feature.player.domain.repository.VideoRepository
import com.example.destest.feature.player.domain.usecase.GetVideo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlayerModule {

    @Provides
    @Singleton
    fun provideGetVideoUseCase(repository: VideoRepository): GetVideo {
        return GetVideo(repository)
    }

    @Provides
    @Singleton
    fun provideVideoRepository(
        db: VideoDatabase,
        api: ContentApi,
    ): VideoRepository {
        return VideoRepositoryImpl(api, db.dao)
    }
}
