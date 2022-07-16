package com.example.pokemon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemon.R
import com.example.pokemon.pojo.Pokemon

class PokemonCardAdapter(val items: ArrayList<Pokemon>): RecyclerView.Adapter<PokemonCardAdapter.PokemonViewHolder>() {

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pokemon_name: TextView = itemView.findViewById(R.id.pokemon_name)
        val pokemon_image: ImageView = itemView.findViewById(R.id.pokemon_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PokemonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false))

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.apply {
            pokemon_name.text = items[position].name
            Glide.with(pokemon_image.context).load(items[position].url).into(pokemon_image)
        }
    }

    override fun getItemCount() = items.size
}