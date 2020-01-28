package com.monstercode.zooapp.room


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
//        ForeignKey(
//            entity = Animal::class,
//            parentColumns = ["id"],
//            childColumns = ["animal_id"],
//            onDelete = ForeignKey.CASCADE
//        ),
//        ForeignKey(
//            entity = Question::class,
//            parentColumns = ["id"],
//            childColumns = ["question_id"],
//            onDelete = ForeignKey.CASCADE
//        )
    ]
)
data class Choice(
    @PrimaryKey
    val id: String,

    val choice: String,

    val question_id: String,

    // the animal that matches the choice
    val animal_id: String


)

