package com.aleexalvz.pokedex.data.repository

import android.util.Log
import com.aleexalvz.pokedex.data.retrofit.model.PokemonBasicInfo
import com.aleexalvz.pokedex.data.retrofit.model.PokemonDetail
import com.aleexalvz.pokedex.data.retrofit.PokeApiInterface
import com.aleexalvz.pokedex.data.retrofit.PokeApiService

class PokeApiRepository: PokemonRepository {

    var pokeApiService: PokeApiInterface = PokeApiService.getPokeApiService()

    override suspend fun getAllPokemons(): List<PokemonBasicInfo> {
        val call = pokeApiService.getAllPokemonBasicInfos()
        var listaPokemons: List<PokemonBasicInfo>? = null
        try {
            listaPokemons = call.execute().body()?.results
        } catch (error: Exception) {
            Log.i("POKEMON", "Não foi possível buscar todos pokemons")
        }
        return listaPokemons ?: mutableListOf()
    }

    override suspend fun getPokemonDetailByName(pokemonName: String): PokemonDetail? {
        val call = pokeApiService.getPokemonDetailByName(pokemonName)
        var pokemon: PokemonDetail? = null
        try {
            pokemon = call.execute().body()
        } catch (error: Exception) {
            Log.i("POKEMON", "Não foi possível localizar o pokemon $pokemonName")
        }
        return pokemon
    }

}