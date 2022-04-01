package com.aleexalvz.pokedex.data.model

import com.google.gson.annotations.SerializedName

class PokeListResults(
    @SerializedName("results")
    val results: List<Pokemon>
)