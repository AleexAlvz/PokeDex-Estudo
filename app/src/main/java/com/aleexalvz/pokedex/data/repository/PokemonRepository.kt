package com.aleexalvz.pokedex.data.repository

import com.aleexalvz.pokedex.data.retrofit.model.PokemonBasicInfo
import com.aleexalvz.pokedex.data.retrofit.model.PokemonDetail

interface PokemonRepository {
    suspend fun getAllPokemons(): List<PokemonBasicInfo>
    suspend fun getPokemonDetailByName(pokemonName: String): PokemonDetail?
}