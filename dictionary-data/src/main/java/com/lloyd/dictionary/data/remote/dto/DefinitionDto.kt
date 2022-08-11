package com.lloyd.dictionary.data.remote.dto

import com.lloyd.dictionary.domain.model.Definition


 data class DefinitionDto(
    val antonyms: List<String>,
    val definition: String?,
    val synonyms: List<String>,
    val example: String?
) {
    fun toDefinition(): Definition {
        return Definition(
            definition = definition,
            antonyms = antonyms,
            synonyms = synonyms,
            example = example
        )
    }
}