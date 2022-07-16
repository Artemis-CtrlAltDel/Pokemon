package com.example.pokemon.repository

import com.example.pokemon.network.PokemonApiService
import com.example.pokemon.pojo.PokemonApiResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class Repository @Inject constructor(val pokemonApiService: PokemonApiService) {
    fun get_pokemons(): Observable<PokemonApiResponse> = pokemonApiService.get_pokemons()
}