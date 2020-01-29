package com.monstercode.zooapp.room

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface CategoryDao {

    @Transaction
    @Query("SELECT * FROM category")
    fun all(): LiveData<List<CategoryWithAnimals>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(category: Category): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(categories: List<Category>)

}