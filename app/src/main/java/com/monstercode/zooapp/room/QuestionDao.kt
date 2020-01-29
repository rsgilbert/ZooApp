package com.monstercode.zooapp.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface QuestionDao {

    @Transaction
    @Query("SELECT * FROM question WHERE category_id=:categoryId")
    fun questionsByCategory(categoryId: String): LiveData<List<QuestionWithChoices>>

    @Transaction
    @Query("SELECT * FROM question WHERE category_id=:categoryId ORDER BY RANDOM() LIMIT 1")
    fun oneQuestionByCategory(categoryId: String): LiveData<QuestionWithChoices>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(question: Question): Long


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(questions: List<Question>)
}

