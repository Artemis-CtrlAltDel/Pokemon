package com.example.pokemon.network

import com.example.pokemon.pojo.PokemonApiResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface PokemonApiService {

    @GET("pokemon")
    fun get_pokemons(): Observable<PokemonApiResponse>
}