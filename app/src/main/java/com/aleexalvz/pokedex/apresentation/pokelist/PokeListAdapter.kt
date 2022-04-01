package com.aleexalvz.pokedex.apresentation.pokelist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aleexalvz.pokedex.R
import com.aleexalvz.pokedex.data.retrofit.model.PokemonDetail

class PokeListAdapter(
    val context: Context
) : RecyclerView.Adapter<PokeListAdapter.PokeListViewHolder>() {

    val pokeList = mutableListOf<PokemonDetail>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeListViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false)
        return PokeListViewHolder(layout)
    }

    override fun getItemCount(): Int = pokeList.size

    override fun onBindViewHolder(holder: PokeListViewHolder, position: Int) {
        holder.name.text = pokeList[position].forms[0].name
        val pokemonNumber = "#${position+1}"
        holder.number.text = pokemonNumber
    }

    fun addPokemonDetail(pokemonDetail: PokemonDetail){
        pokeList.add(pokemonDetail)
    }

    class PokeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val number = itemView.findViewById<TextView>(R.id.item_pokemon_number)
        val name = itemView.findViewById<TextView>(R.id.item_pokemon_name)
    }
}