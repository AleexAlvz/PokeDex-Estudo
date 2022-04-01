package com.aleexalvz.pokedex.data.retrofit.model

import com.google.gson.annotations.SerializedName

class PokemonBasicInfo(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)