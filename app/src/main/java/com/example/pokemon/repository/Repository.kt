package com.example.pokemon.repository

import androidx.lifecycle.LiveData
import com.example.pokemon.db.PokemonDao
import com.example.pokemon.network.PokemonApiService
import com.example.pokemon.pojo.Pokemon
import com.example.pokemon.pojo.PokemonApiResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class Repository @Inject constructor(val pokemon_api_service: PokemonApiService, val pokemon_dao: PokemonDao) {
    fun get_pokemons() = pokemon_api_service.get_pokemons()

    fun insert_pokemon(pokemon: Pokemon) = pokemon_dao.insert_pokemon(pokemon)
    fun delete_pokemon(pokemon_name: String) = pokemon_dao.delete_pokemon(pokemon_name)
    fun get_all(): LiveData<List<Pokemon>> = pokemon_dao.get_all()
}