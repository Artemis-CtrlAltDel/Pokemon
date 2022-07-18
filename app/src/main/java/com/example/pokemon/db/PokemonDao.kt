package com.example.pokemon.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemon.pojo.Pokemon

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert_pokemon(pokemon: Pokemon)

    @Query("DELETE FROM pokemon_table WHERE name = :pokemon_name")
    fun delete_pokemon(pokemon_name: String)

    @Query("SELECT * FROM pokemon_table")
    fun get_all(): LiveData<List<Pokemon>>
}