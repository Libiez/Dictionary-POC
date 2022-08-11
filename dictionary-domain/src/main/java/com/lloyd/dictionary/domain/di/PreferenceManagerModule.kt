package com.lloyd.dictionary.domain.di

import com.lloyd.dictionary.domain.preference.PreferenceHelper
import com.lloyd.dictionary.domain.preference_case.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PreferenceManagerModule {

    @Provides
    @Singleton
    fun providePreferenceManager(preferenceHelper : PreferenceHelper): PreferenceManager {
        return PreferenceManager(preferenceHelper)
    }
}