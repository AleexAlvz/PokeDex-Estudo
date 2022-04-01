package com.aleexalvz.pokedex.data.model

import com.google.gson.annotations.SerializedName

class Type(
    @SerializedName("name")
    val typeName: String
)