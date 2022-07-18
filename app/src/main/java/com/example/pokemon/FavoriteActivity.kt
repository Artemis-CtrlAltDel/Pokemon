package com.example.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.adapters.OnItemClick
import com.example.pokemon.adapters.PokemonCardAdapter
import com.example.pokemon.databinding.ActivityFavoriteBinding
import com.example.pokemon.pojo.Pokemon
import com.example.pokemon.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    private lateinit var adapter: PokemonCardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFavoriteBinding.inflate(layoutInflater)

        binding.apply {
            setContentView(root)

            title = "Favorites"

            val view_model = ViewModelProvider(this@FavoriteActivity)[PokemonViewModel::class.java]
            adapter = PokemonCardAdapter(
                arrayListOf(),
                object : OnItemClick{
                    override fun on_item_click(position: Int) {
                        remove_item(position, view_model)
                    }
                }
            )

            view_model.get_all()
            view_model.pokemon_fav_list?.observe(this@FavoriteActivity)
            {
                errorMessageContainer.visibility = if (it.isEmpty()) View.VISIBLE else View.INVISIBLE
                adapter.set_items(ArrayList(it))
            }

            recycler.adapter = adapter
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

            homeButton.setOnClickListener {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finishAffinity()
            }
        }
    }

    fun remove_item(position: Int, view_model: PokemonViewModel){
        val item = adapter.item_at(position)

        view_model.delete_pokemon(item.name)
    }
}