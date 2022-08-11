package com.lloyd.dictionary.data.di

import com.lloyd.dictionary.data.preferences.AppPreferenceHelper
import com.lloyd.dictionary.data.preferences.UserPreferences
import com.lloyd.dictionary.domain.preference.PreferenceHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {

    @Provides
    @Singleton
    fun providePreferenceHelper(userPreferences: UserPreferences): PreferenceHelper {
        return AppPreferenceHelper(userPreferences)
    }
}