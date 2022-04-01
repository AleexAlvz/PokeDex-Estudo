package com.aleexalvz.pokedex.data.retrofit.model

import com.google.gson.annotations.SerializedName

class PokemonDetail(
    @SerializedName("forms")
    val forms: List<Form>,
    @SerializedName("sprites")
    val sprites: Sprites,
    @SerializedName("types")
    val types: List<Types>,
    @SerializedName("weight")
    val weight: String
)