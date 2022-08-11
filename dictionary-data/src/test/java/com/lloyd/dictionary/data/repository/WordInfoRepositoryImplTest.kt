package com.lloyd.dictionary.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lloyd.dictionary.data.dummyWordsInfo
import com.lloyd.dictionary.data.local.WordInfoDao
import com.lloyd.dictionary.data.remote.DictionaryApi
import com.lloyd.dictionary.domain.model.Resource
import com.lloyd.dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WordInfoRepositoryImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var wordInfoRepository : WordInfoRepository

    @Mock
    lateinit var wordInfoDao : WordInfoDao

    @Mock
    lateinit var  api: DictionaryApi

    @Before
    fun setup(){
        wordInfoRepository = WordInfoRepositoryImpl(api,wordInfoDao)
    }

    @Test
    fun `When wordInfo return success data `() = runBlocking {

        val dummyResult = dummyWordsInfo()
        val testWordInfo = Resource.Success(dummyResult)

        `when`(wordInfoDao.getWordsInfo("bank")).thenReturn(testWordInfo.data)

        val result = wordInfoRepository.getWordInfo("bank").toList()

        assert(result.isNotEmpty())



    }



}