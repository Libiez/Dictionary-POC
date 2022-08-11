package com.lloyd.dictionary.domain.di

import com.lloyd.dictionary.domain.repository.WordInfoRepository
import com.lloyd.dictionary.domain.use_cases.GetWordInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideGetWordInfoUseCases(repository: WordInfoRepository): GetWordInfo {
        return GetWordInfo(repository)
    }
}