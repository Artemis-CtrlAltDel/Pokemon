package com.example.pokemon.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemon.R
import com.example.pokemon.pojo.Pokemon
import com.example.pokemon.viewmodel.PokemonViewModel

class PokemonCardAdapter(var items: ArrayList<Pokemon>, val on_item_click: OnItemClick): RecyclerView.Adapter<PokemonCardAdapter.PokemonViewHolder>() {

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pokemon_name: TextView = itemView.findViewById(R.id.pokemon_name)
        val pokemon_image: ImageView = itemView.findViewById(R.id.pokemon_image)
        val pokemon_favorite: ImageView = itemView.findViewById(R.id.add_favorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PokemonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false))

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.apply {
            val context = pokemon_name.context

            pokemon_name.text = items[position].name
            Glide.with(context).load(items[position].url).into(pokemon_image)

            pokemon_favorite.setImageDrawable(
                context.getDrawable(
                    when(items[position].is_favorite){
                        true -> R.drawable.ic_star_active
                        else -> R.drawable.ic_star_inactive
                    }
                )
            )

            pokemon_favorite.setOnClickListener {
                items[position].is_favorite = !items[position].is_favorite
                on_item_click.on_item_click(position)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount() = items.size

    fun set_items(list: ArrayList<Pokemon>) {
        items = list
        notifyDataSetChanged()
    }

    fun item_at(position: Int) = items[position]
}