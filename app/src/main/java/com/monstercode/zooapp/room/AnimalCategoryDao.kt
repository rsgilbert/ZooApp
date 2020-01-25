package com.monstercode.zooapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface AnimalCategoryDao {
    @Query("SELECT * FROM animalcategory")
    fun all(): LiveData<List<AnimalCategory>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(animalCategory: AnimalCategory): Long

}