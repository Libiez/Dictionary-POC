package com.lloyd.dictionary.domain.model

data class Definition(
    val antonyms: List<String>?,
    val definition: String?,
    val synonyms: List<String>?,
    val example: String?
)