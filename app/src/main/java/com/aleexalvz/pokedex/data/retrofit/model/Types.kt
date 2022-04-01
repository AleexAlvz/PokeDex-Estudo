package com.aleexalvz.pokedex.data.retrofit.model

import com.google.gson.annotations.SerializedName

class Types(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: Type
)