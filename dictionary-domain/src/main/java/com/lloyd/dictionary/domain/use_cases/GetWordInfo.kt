package com.lloyd.dictionary.domain.use_cases

import com.lloyd.dictionary.domain.model.Resource
import com.lloyd.dictionary.domain.model.WordInfo
import com.lloyd.dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(private val repository: WordInfoRepository) {

    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()) {
            return flow { }
        }
        return repository.getWordInfo(word)
    }

}