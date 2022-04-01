package com.aleexalvz.pokedex.data.model

import com.google.gson.annotations.SerializedName

class Sprites(
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("back_default")
    val backDefault: String,
    @SerializedName("front_female")
    val frontFemale: String,
    @SerializedName("back_female")
    val backFemale: String
)