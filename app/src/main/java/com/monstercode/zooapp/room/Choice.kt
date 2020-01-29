package com.monstercode.zooapp.room


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Choice(
    @PrimaryKey
    val id: String,

    val question_id: String,

    val choice: String,

    val correct: Boolean,

    val info: String


) : Serializable

