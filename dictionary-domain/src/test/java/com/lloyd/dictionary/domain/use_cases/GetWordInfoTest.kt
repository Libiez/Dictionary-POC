package com.lloyd.dictionary.domain.use_cases


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lloyd.dictionary.domain.model.Resource
import com.lloyd.dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
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
class GetWordInfoTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var wordInfoRepository: WordInfoRepository
    private lateinit var getWordInfo: GetWordInfo

    @Before
    fun setUp() {
        getWordInfo = GetWordInfo(wordInfoRepository)
    }

    @Test
    fun `When response returns Success`() = runBlocking {
        val inputFlow = flowOf(Resource.Success(dummyWordsInfo().map { it.toWordInfo() }))
        `when`(wordInfoRepository.getWordInfo("bank")).thenReturn(inputFlow)
        val output = getWordInfo.invoke("bank").toList()
        assert(output[0].data?.size == 1)
    }

}
