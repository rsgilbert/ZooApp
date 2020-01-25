package com.monstercode.zooapp.room

import androidx.room.Entity

@Entity(tableName = "questions")
data class Question(
    val _id: String,
    val question: String,
    val choice_1: String

)

