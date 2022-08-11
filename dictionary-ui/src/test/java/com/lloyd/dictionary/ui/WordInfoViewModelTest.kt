package com.lloyd.dictionary.ui

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.lloyd.dictionary.data.preferences.AppPreferenceHelper
import com.lloyd.dictionary.data.preferences.UserPreferences
import com.lloyd.dictionary.domain.model.Resource
import com.lloyd.dictionary.domain.preference_case.PreferenceManager
import com.lloyd.dictionary.domain.use_cases.GetWordInfo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class WordInfoViewModelTest {

    private lateinit var viewModel: WordInfoViewModel
    private lateinit var userPreferences: UserPreferences
    private lateinit var preferenceManager: PreferenceManager
    lateinit var getWordInfo: GetWordInfo
    private lateinit var fakeWordInfoRepositoryTest: WordInfoRepositoryDataSet

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        userPreferences = UserPreferences(context)
        preferenceManager = PreferenceManager(AppPreferenceHelper(userPreferences))
        fakeWordInfoRepositoryTest = WordInfoRepositoryDataSet()
        getWordInfo = GetWordInfo(fakeWordInfoRepositoryTest)
        viewModel = WordInfoViewModel(getWordInfo, preferenceManager)

    }

    @Test
    fun testSuccessSearch() = kotlinx.coroutines.test.runTest {
        val firstItem = getWordInfo("bank").first()
        assertThat(firstItem.data?.get(0)?.word.equals("bank"))
    }

    @Test
    fun testErrorSearch() = kotlinx.coroutines.test.runTest {

        fakeWordInfoRepositoryTest.wordFlow = flow {
            emit(Resource.Error(data = emptyList(), message = "Unknown Error"))
            delay(3000L)
        }

        val firstItem = getWordInfo("bank").first()
        assertThat(firstItem.message?.equals("Unknown Error"))
    }

    @Test
    fun testLoadingSearch() = kotlinx.coroutines.test.runTest {

        fakeWordInfoRepositoryTest.wordFlow = flow {
            emit(Resource.Loading(data = emptyList()))
            delay(3000L)
        }

        getWordInfo("bank").onEach { result ->
            assertThat(result is Resource.Loading)
        }
    }


}