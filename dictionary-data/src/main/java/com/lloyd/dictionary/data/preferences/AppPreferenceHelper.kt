package com.lloyd.dictionary.data.preferences

import com.lloyd.dictionary.domain.preference.PreferenceHelper
import kotlinx.coroutines.flow.first

class AppPreferenceHelper(private val userPreferences: UserPreferences) : PreferenceHelper {

    override suspend fun isLaunched(): String  {
       return userPreferences.isFirstLaunch.first()?:""
    }
    override suspend fun saveLaunch(launch: String) {
       userPreferences.saveLaunch(launch)
    }
}