package com.lloyd.dictionary.data.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.lloyd.dictionary.data.local.Converters
import com.lloyd.dictionary.data.local.WordInfoDatabase
import com.lloyd.dictionary.data.remote.DictionaryApi
import com.lloyd.dictionary.data.repository.WordInfoRepositoryImpl
import com.lloyd.dictionary.data.util.GsonParser
import com.lloyd.dictionary.domain.repository.WordInfoRepository
import com.lloyd.dictionary.domain.use_cases.GetWordInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModuleTest{

    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, WordInfoDatabase::class.java)
            .addTypeConverter(Converters(GsonParser(Gson())))
            .allowMainThreadQueries()
            .build()

    @Provides
    fun provideGetWordInfoUseCases(repository: WordInfoRepository): GetWordInfo {
        return GetWordInfo(repository)
    }


    @Provides
    fun provideWordInfoRepository(db: WordInfoDatabase, api: DictionaryApi): WordInfoRepository {
        return WordInfoRepositoryImpl(api, db.dao)
    }
}