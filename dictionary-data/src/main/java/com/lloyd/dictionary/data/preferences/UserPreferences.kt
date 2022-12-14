package com.lloyd.dictionary.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = UserPreferences.APP_PREFERENCES)

class UserPreferences @Inject constructor(@ApplicationContext context: Context) {

    private val applicationContext = context.applicationContext

    suspend fun saveLaunch(launch: String) {
        applicationContext.dataStore.edit { preference ->
            preference[key_launch] = launch
        }
    }

    val isFirstLaunch: Flow<String?>
        get() = applicationContext.dataStore.data.map { preference ->
            preference[key_launch]
        }

    companion object {
        const val APP_PREFERENCES = "app_preferences"
        private val key_launch = stringPreferencesKey("pref_launch")
    }

}
