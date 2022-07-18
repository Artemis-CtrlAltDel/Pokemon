package com.example.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.adapters.OnItemClick
import com.example.pokemon.adapters.PokemonCardAdapter
import com.example.pokemon.databinding.ActivityMainBinding
import com.example.pokemon.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var adapter: PokemonCardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        binding.apply {
            setContentView(root)

            title = "Home"

            val view_model = ViewModelProvider(this@MainActivity)[PokemonViewModel::class.java]
            adapter = PokemonCardAdapter(
                arrayListOf(),
                object : OnItemClick{
                    override fun on_item_click(position: Int) {
                        favorite_item(position, view_model)
                    }
                }
            )

            view_model.get_pokemons()
            view_model.pokemon_list.observe(this@MainActivity)
            {
//                Log.e("med api response : ", "$it")
                errorMessageContainer.visibility = if (it.isEmpty()) View.VISIBLE else View.INVISIBLE
                adapter.set_items(it)
            }

            recycler.adapter = adapter
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

            favoriteButton.setOnClickListener {
                startActivity(Intent(applicationContext, FavoriteActivity::class.java))
                finishAffinity()
            }
        }
    }

    fun favorite_item(position: Int, view_model: PokemonViewModel){

        val item = adapter.item_at(position)

        when(item.is_favorite){
            true -> view_model.insert_pokemon(item)
            else -> view_model.delete_pokemon(item.name)
        }
    }
}