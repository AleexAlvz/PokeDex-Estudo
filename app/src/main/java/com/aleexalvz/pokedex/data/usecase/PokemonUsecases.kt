package com.aleexalvz.pokedex.data.usecase

import com.aleexalvz.pokedex.data.model.PokemonDetail
import com.aleexalvz.pokedex.data.repository.PokemonRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonUsecases(val pokemonRepository: PokemonRepository) {

    suspend fun getAllPokemons() =
        pokemonRepository.getAllPokemons()

    suspend fun getPokemonDetailByName(pokemonName: String) =
        pokemonRepository.getPokemonDetailByName(pokemonName)

    suspend fun getAllPokemonDetails(): Flow<PokemonDetail> = flow {
        val pokemonList = getAllPokemons()
        for (pokemon in pokemonList) {
            val pokemonDetail = getPokemonDetailByName(pokemon.name)
            pokemonDetail?.let {
                emit(it)
                delay(20)
            }
        }
    }
}