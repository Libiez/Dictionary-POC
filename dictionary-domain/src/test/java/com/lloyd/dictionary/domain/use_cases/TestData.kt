package com.lloyd.dictionary.domain.use_cases


import com.lloyd.dictionary.data.local.entity.WordInfoEntity
import com.lloyd.dictionary.domain.model.Definition
import com.lloyd.dictionary.domain.model.Meaning

fun dummyWordsInfo() :List<WordInfoEntity> {
    val definitions = mutableListOf<Definition>()
    for (i in 1..5) {
        definitions.add(
            Definition(
                definition = "institution $i",
                example = "bloodBank $i",
                synonyms = null,
                antonyms = null
            )
        )
    }
    val meaning1 = Meaning(definitions, "noun")
    val meaning2 = Meaning(definitions, "noun")
    val meanings: List<Meaning> = listOf(meaning1, meaning2)
    val wordInfoEntity = WordInfoEntity(meanings, "bæŋk", "bank")
    return listOf(wordInfoEntity)
}
