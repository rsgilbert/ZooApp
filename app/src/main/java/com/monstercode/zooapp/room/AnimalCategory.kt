package com.monstercode.zooapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AnimalCategory(
    @PrimaryKey
    val id: String,

    val name: String,

    val summary: String? = "",

    val info: String? = null,

    val profile_image: String? = ""
)