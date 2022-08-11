package com.lloyd.dictionary.data.remote.dto

import com.lloyd.dictionary.domain.model.Meaning


  data class MeaningDto(
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String,
) {

    fun toMeaning(): Meaning {
        return Meaning(
            definitions = definitions.map { it.toDefinition() },
            partOfSpeech = partOfSpeech
        )
    }
}