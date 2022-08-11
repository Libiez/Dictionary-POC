package com.lloyd.dictionary.data.di


import com.lloyd.dictionary.data.local.WordInfoDatabase
import com.lloyd.dictionary.data.remote.DictionaryApi
import com.lloyd.dictionary.data.repository.WordInfoRepositoryImpl
import com.lloyd.dictionary.domain.repository.WordInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWordInfoRepository(db: WordInfoDatabase, api: DictionaryApi): WordInfoRepository {
        return WordInfoRepositoryImpl(api, db.dao)
    }

}