package com.monstercode.zooapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ImageDao {

    @Query("SELECT * FROM image WHERE animal_id=:animalId")
    fun imagesByAnimal(animalId: String) : LiveData<List<Image>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(image: Image) : Long
}