package com.aleexalvz.pokedex.data.retrofit.model

import com.google.gson.annotations.SerializedName

class PokeListResults(
    @SerializedName("results")
    val results: List<PokemonBasicInfo>
)