package com.aleexalvz.pokedex.data.model

data class Pokemon(
    val index: Int,
    val name: String,
    val spriteURL: String,
    val types: List<String>,
    val weight: String,
    val favorite: Boolean = false
)