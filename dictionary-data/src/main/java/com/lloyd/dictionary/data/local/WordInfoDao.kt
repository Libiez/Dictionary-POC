package com.lloyd.dictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lloyd.dictionary.data.local.entity.WordInfoEntity

@Dao
interface WordInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfo(info:List<WordInfoEntity>)

    @Query("DELETE FROM wordInfoEntity WHERE word IN(:words)")
    suspend fun deleteWordInfo(words:List<String>)

    @Query("SELECT * FROM wordInfoEntity WHERE word LIKE '%' || :word || '%'")
    suspend fun getWordsInfo(word:String):List<WordInfoEntity>

}