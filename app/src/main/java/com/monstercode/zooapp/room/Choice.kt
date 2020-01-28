package com.monstercode.zooapp.room


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Choice(
    @PrimaryKey
    val id: String,

    val choice: String,

    val question_id: String,

    // the animal that matches the choice
    val animal_id: String


)

