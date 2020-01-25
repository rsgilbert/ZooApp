package com.monstercode.zooapp.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [
        ForeignKey(
            entity = AnimalCategory::class,
            parentColumns = ["id"],
            childColumns = ["animal_category_id"],
            onDelete = CASCADE
        )
    ]
)

data class Animal(
    @PrimaryKey
    val id: String,

    val english_name: String? = null,

    val scientific_name: String? = null,

    val animal_category_id: String,

    val summary: String? = null,

    val description: String? = null,

    val count: Int? = null,

    val profile_image: String? = null
)