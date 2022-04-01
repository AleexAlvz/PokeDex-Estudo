package com.aleexalvz.pokedex.data.model

import com.google.gson.annotations.SerializedName

class Types(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: Type
)