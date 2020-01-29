package com.monstercode.zooapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.io.Serializable

@Entity
data class Category(
    @PrimaryKey
    val id: String,

    val name: String,

    val info: String? = null,

    val picture: String? = null,

    val count: Int,

    val level: String

) : Serializable

data class CategoryWithAnimals(
    val id: String,

    val name: String,

    val info: String? = null,

    val picture: String? = null,

    val count: Int,

    val level: String,

    @Relation(parentColumn = "id", entityColumn = "category_id")
    val animals: List<Animal>

) : Serializable