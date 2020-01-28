package com.monstercode.zooapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AnimalDao {


    @Query("SELECT * FROM animal WHERE category_id=:categoryId")
    fun animalsByCategory(categoryId: String): LiveData<List<Animal>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(animal: Animal): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(animals: List<Animal>)


}