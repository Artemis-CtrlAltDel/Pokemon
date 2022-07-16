package com.example.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.adapters.PokemonCardAdapter
import com.example.pokemon.databinding.ActivityMainBinding
import com.example.pokemon.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        var adapter = PokemonCardAdapter(arrayListOf())
        val view_model = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(PokemonViewModel::class.java)

        binding.apply {
            view_model.get_pokemons()
            view_model.pokemon_list.observe(this@MainActivity)
            {
                adapter = PokemonCardAdapter(it)
            }

            recycler.adapter = adapter
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

            setContentView(root)
        }
    }
}