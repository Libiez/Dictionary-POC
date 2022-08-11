package com.lloyd.dictionary.ui.compose

import com.lloyd.dictionary.domain.model.WordInfo


data class WordInfoState(

    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false

)
