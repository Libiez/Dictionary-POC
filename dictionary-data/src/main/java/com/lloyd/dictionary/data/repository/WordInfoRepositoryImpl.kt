package com.lloyd.dictionary.data.repository

import com.lloyd.dictionary.domain.model.Resource
import com.lloyd.dictionary.domain.model.WordInfo
import com.lloyd.dictionary.data.local.WordInfoDao
import com.lloyd.dictionary.data.remote.DictionaryApi
import com.lloyd.dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

internal class WordInfoRepositoryImpl(private val api: DictionaryApi,
                             private val dao: WordInfoDao): WordInfoRepository {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {

        emit(Resource.Loading())

        val wordInfo = dao.getWordsInfo(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfo))

        try {

            val remoteWordInfo = api.getWordInfo(word)
            dao.deleteWordInfo(remoteWordInfo.map { it.word })
            dao.insertWordInfo(remoteWordInfo.map { it.toWordInfoEntity() })

        } catch (e: HttpException) {
            emit(Resource.Error(
                    message = "Oops, Something went wrong "
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error(
                    message = " Couldn't reach the network, Please check you network connection"
                )
            )
        }
        val newWordInfo = dao.getWordsInfo(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfo))
    }
}