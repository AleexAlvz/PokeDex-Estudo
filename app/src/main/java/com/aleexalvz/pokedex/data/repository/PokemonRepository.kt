package com.aleexalvz.pokedex.data.repository

import com.aleexalvz.pokedex.data.model.Pokemon
import com.aleexalvz.pokedex.data.model.PokemonDetail

interface PokemonRepository {
    suspend fun getAllPokemons(): List<Pokemon>
    suspend fun getPokemonDetailByName(pokemonName: String): PokemonDetail?
}