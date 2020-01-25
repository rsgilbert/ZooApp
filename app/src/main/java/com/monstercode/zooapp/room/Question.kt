package com.monstercode.zooapp.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = AnimalCategory::class,
            parentColumns = ["id"],
            childColumns = ["animal_category_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Question(
    @PrimaryKey
    val id: String,

    val question: String,

    val animal_category_id: String

)

