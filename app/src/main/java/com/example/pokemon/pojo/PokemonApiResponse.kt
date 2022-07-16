package com.example.pokemon.pojo

data class PokemonApiResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val pokemons: ArrayList<Pokemon>)
