package com.monstercode.zooapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.io.Serializable

@Entity
data class Question(
    @PrimaryKey
    val id: String,

    val question: String,

    val category_id: String

) : Serializable

data class QuestionWithChoices(
    @PrimaryKey
    val id: String,

    val question: String,

    val category_id: String,

    @Relation(parentColumn="id", entityColumn = "question_id")
    val choices: List<Choice>

) : Serializable

