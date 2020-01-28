package com.monstercode.zooapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CategoryDao {

    @Query("SELECT * FROM category")
    fun all(): LiveData<List<Category>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(category: Category): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(categories: List<Category>)

}