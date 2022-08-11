package com.lloyd.dictionary.data.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.lloyd.dictionary.data.local.Converters
import com.lloyd.dictionary.data.local.WordInfoDatabase
import com.lloyd.dictionary.data.util.GsonParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase {
        return Room.databaseBuilder(app, WordInfoDatabase::class.java, "word_db")
            .addTypeConverter(Converters(GsonParser(Gson()))).build()
    }

    @Singleton
    @Provides
    fun provideWordInfoDao(database: WordInfoDatabase) = database.dao

}