package com.monstercode.zooapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuestionDao {

    @Query("SELECT * FROM question WHERE animal_category_id=:animal_category_id")
    fun questionsByCategory(animal_category_id: String): LiveData<List<Question>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(question: Question): Long


}

