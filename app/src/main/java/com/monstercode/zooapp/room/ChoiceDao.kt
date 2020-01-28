package com.monstercode.zooapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ChoiceDao {

    @Query("SELECT * FROM choice")
    fun all() : LiveData<List<Choice>>


    @Query("SELECT * FROM choice WHERE question_id=:questionId")
    fun choicesByQuestion(questionId: String) : LiveData<List<Choice>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(choice: Choice) : Long


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(choices: List<Choice>)
}