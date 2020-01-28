package com.monstercode.zooapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Animal(
    @PrimaryKey
    val id: String,

    val name: String? = null,

    val category_id: String,

    val summary: String? = null,

    val description: String? = null,

    val count: Int? = null,

    val profile_image: String? = null
)