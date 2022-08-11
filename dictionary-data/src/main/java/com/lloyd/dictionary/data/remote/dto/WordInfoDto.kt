package com.lloyd.dictionary.data.remote.dto

import com.lloyd.dictionary.data.local.entity.WordInfoEntity

  data class WordInfoDto(
    val meanings: List<MeaningDto>,
    val phonetic: String?,
    val phonetics: List<PhoneticDto>,
    val word: String
) {
    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity(
            meanings = meanings.map { it.toMeaning() },
            phonetic = phonetic,
            word = word
        )
    }
}