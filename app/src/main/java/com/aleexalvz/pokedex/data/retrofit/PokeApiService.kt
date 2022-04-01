package com.aleexalvz.pokedex.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokeApiService {

    fun getPokeApiService(): PokeApiInterface =
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApiInterface::class.java)
}
