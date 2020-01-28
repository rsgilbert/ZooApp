package com.monstercode.zooapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuestionDao {

    @Query("SELECT * FROM question WHERE category_id=:categoryId")
    fun questionsByCategory(categoryId: String): LiveData<List<QuestionWithChoices>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(question: Question): Long


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(questions: List<Question>)
}

