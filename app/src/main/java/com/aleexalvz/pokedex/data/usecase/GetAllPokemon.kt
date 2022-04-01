package com.aleexalvz.pokedex.data.usecase

import com.aleexalvz.pokedex.data.retrofit.model.PokemonDetail
import com.aleexalvz.pokedex.data.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetAllPokemon(private val pokemonRepository: PokemonRepository) {
    operator fun invoke(): Flow<PokemonDetail> = flow {
        val pokemonList = pokemonRepository.getAllPokemons()
        for (pokemon in pokemonList) {
            val pokemonDetail = pokemonRepository.getPokemonDetailByName(pokemon.name)
            pokemonDetail?.let {
                emit(pokemonDetail)
                delay(10)
            }
        }
    }.flowOn(IO)
}