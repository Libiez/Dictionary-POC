package com.lloyd.dictionary.domain.preference


interface PreferenceHelper {

    suspend fun isLaunched(): String?

    suspend fun saveLaunch(launch: String)

}