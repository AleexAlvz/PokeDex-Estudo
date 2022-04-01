package com.aleexalvz.pokedex.data.retrofit

import com.aleexalvz.pokedex.data.model.PokeListResults
import com.aleexalvz.pokedex.data.model.Pokemon
import com.aleexalvz.pokedex.data.model.PokemonDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiInterface {

    @GET("pokemon?limit=1126&offset=0")
    fun getAllPokemons(): Call<PokeListResults>

    @GET("pokemon/{pokemonName}")
    fun getPokemonByName(@Path("pokemonName") pokemonName: String): Call<PokemonDetail>
}