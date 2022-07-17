package com.example.pokemon.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
data class Pokemon(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    var url: String,
    var is_favorite: Boolean = false)
