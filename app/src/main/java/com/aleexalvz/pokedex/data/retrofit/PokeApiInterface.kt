package com.aleexalvz.pokedex.data.retrofit

import com.aleexalvz.pokedex.data.retrofit.model.PokeListResults
import com.aleexalvz.pokedex.data.retrofit.model.PokemonDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiInterface {

    @GET("pokemon?limit=1126&offset=0")
    fun getAllPokemonBasicInfos(): Call<PokeListResults>

    @GET("pokemon/{pokemonName}")
    fun getPokemonDetailByName(@Path("pokemonName") pokemonName: String): Call<PokemonDetail>
}