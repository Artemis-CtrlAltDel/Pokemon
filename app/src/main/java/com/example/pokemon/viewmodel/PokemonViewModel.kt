package com.example.pokemon.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemon.pojo.Pokemon
import com.example.pokemon.pojo.PokemonApiResponse
import com.example.pokemon.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(val repository: Repository): ViewModel() {
    val pokemon_list: MutableLiveData<ArrayList<Pokemon>> = MutableLiveData(arrayListOf())

    @SuppressLint("CheckResult")
    fun get_pokemons() {
        repository.get_pokemons()
            .subscribeOn(Schedulers.io())
            .map { pokemon_api_response: PokemonApiResponse ->
                val list: ArrayList<Pokemon> = pokemon_api_response.results
                for (pokemon: Pokemon in list) {
                    val url = pokemon.url
                    val pokemon_index = url.split("/").toTypedArray()
                    pokemon.url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/" + pokemon_index[pokemon_index.size - 1] + ".png"
                }
                list
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> pokemon_list.value = result }
            ) { error -> Log.e("bonk viewmodel", error.message.toString()) }
    }
}