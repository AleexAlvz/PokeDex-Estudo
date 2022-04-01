package com.aleexalvz.pokedex.data.usecase

import com.aleexalvz.pokedex.data.model.Pokemon
import com.aleexalvz.pokedex.data.repository.PokemonRepository
import com.aleexalvz.pokedex.utils.PokemonUtils
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetAllPokemonUseCase(private val pokemonRepository: PokemonRepository) {
    operator fun invoke(): Flow<Pokemon> = flow {
        val pokemonList = pokemonRepository.getAllPokemons()
        for (pokemon in pokemonList) {
            val pokemonDetail = pokemonRepository.getPokemonDetailByName(pokemon.name)
            pokemonDetail?.let {
                val pokemon = PokemonUtils.WrapperPokemon(pokemonDetail)
                emit(pokemon)
                delay(10)
            }
        }
    }.flowOn(IO)
}