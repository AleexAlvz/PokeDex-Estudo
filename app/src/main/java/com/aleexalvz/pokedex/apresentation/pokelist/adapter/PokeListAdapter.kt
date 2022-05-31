package com.aleexalvz.pokedex.apresentation.pokelist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aleexalvz.pokedex.R
import com.aleexalvz.pokedex.data.model.Pokemon
import com.bumptech.glide.Glide

class PokeListAdapter(
    private val context: Context
) : RecyclerView.Adapter<PokeListAdapter.PokeListViewHolder>() {

    private val pokeList = mutableListOf<Pokemon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeListViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false)
        return PokeListViewHolder(layout)
    }

    override fun getItemCount(): Int = pokeList.size

    override fun onBindViewHolder(holder: PokeListViewHolder, position: Int) {
        val pokemon = pokeList[position]

        holder.name.text = pokemon.name

        val pokemonNumber = "#${pokemon.index}"
        holder.number.text = pokemonNumber

        if (pokemon.spriteURL.isNotBlank()){
            Glide.with(context).load(pokemon.spriteURL).into(holder.image)
        }
    }

    fun addPokemon(pokemon: Pokemon) {
        pokeList.add(pokemon)
    }

    fun clearList() {
        pokeList.clear()
    }

    class PokeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val number: TextView = itemView.findViewById(R.id.item_pokemon_number)
        val name: TextView = itemView.findViewById(R.id.item_pokemon_name)
        val image: ImageView = itemView.findViewById(R.id.item_pokemon_image)
    }
}