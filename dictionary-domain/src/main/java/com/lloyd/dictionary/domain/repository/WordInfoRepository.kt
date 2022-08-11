package com.lloyd.dictionary.domain.repository

import com.lloyd.dictionary.domain.model.Resource
import com.lloyd.dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

   fun getWordInfo(word:String) : Flow<Resource<List<WordInfo>>>

}