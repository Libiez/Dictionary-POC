package com.lloyd.dictionary.domain.preference_case

import com.lloyd.dictionary.domain.preference.PreferenceHelper


class PreferenceManager(private val preferenceHelper: PreferenceHelper) {

    suspend fun isLaunched(): String? = preferenceHelper.isLaunched()

    suspend fun saveLaunch(launch: String) {
        preferenceHelper.saveLaunch(launch)
    }

}