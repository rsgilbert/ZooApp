package com.monstercode.zooapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
data class Animal(
    @PrimaryKey
    val id: String,

    val name: String? = null,

    val category_id: String? = null,

    val summary: String? = null,

    val description: String? = null,

    val location: String? = null,

    val count: Int? = null,

    val level: String? = null,

    val picture: String? = null

) : Serializable