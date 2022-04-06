package com.aleexalvz.pokedex.utils

import com.aleexalvz.pokedex.data.model.Pokemon
import com.aleexalvz.pokedex.data.retrofit.model.Form
import com.aleexalvz.pokedex.data.retrofit.model.PokemonDetail
import com.aleexalvz.pokedex.data.retrofit.model.Types

object PokemonUtils {

    fun WrapperPokemon(pokemonDetail: PokemonDetail): Pokemon {
        val index = extractIndexOfFormUrl(pokemonDetail.forms[0])
        val name = pokemonDetail.forms[0].name
        val spriteUrl = pokemonDetail.sprites.frontDefault ?: ""
        val types = extractTypeStringOfTypes(pokemonDetail.types)
        val weight = pokemonDetail.weight

        return Pokemon(index, name, spriteUrl, types, weight)
    }

    // sample form url: "https://pokeapi.co/api/v2/pokemon-form/132/"
    fun extractIndexOfFormUrl(form: Form): Int =
        form.url.removePrefix("https://pokeapi.co/api/v2/pokemon-form/").removeSuffix("/").toInt()

    fun extractTypeStringOfTypes(types: List<Types>): List<String> {
        val typeList = mutableListOf<String>()
        for (type in types) {
            val typeName = type.type.typeName
            typeList.add(typeName)
        }
        return typeList
    }
}
