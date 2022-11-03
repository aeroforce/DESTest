package com.example.destest.core.di

import com.example.destest.core.service.appstate.AppStateService
import com.example.destest.core.service.appstate.AppStateServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideAppStateService(): AppStateService {
        return AppStateServiceImpl()
    }
}
