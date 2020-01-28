package com.monstercode.zooapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey
    val id: String,

    val name: String,

    val summary: String? = null,

    val description: String? = null,

    val profile_image: String? = null
)