package com.monstercode.zooapp.room


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Image (
    @PrimaryKey
    val id: String,

    val src: String,

    val animal_id: String

) : Serializable

