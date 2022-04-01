package com.aleexalvz.pokedex.data.retrofit.model

import com.google.gson.annotations.SerializedName

class Type(
    @SerializedName("name")
    val typeName: String
)