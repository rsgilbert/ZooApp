package com.monstercode.zooapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animals")
data class Animal(
    @PrimaryKey
    @ColumnInfo
    val id: String,


    val name: String,
    val summary: String?,
    val profile_image: String?
)