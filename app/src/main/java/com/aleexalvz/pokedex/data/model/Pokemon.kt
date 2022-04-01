package com.aleexalvz.pokedex.data.model

import com.google.gson.annotations.SerializedName

class Pokemon(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)