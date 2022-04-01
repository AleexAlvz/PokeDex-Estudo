package com.aleexalvz.pokedex.apresentation.pokelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aleexalvz.pokedex.data.model.Pokemon
import com.aleexalvz.pokedex.data.repository.PokeApiRepository
import com.aleexalvz.pokedex.data.usecase.GetAllPokemonUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PokeListViewModel: ViewModel() {

    private val getAllPokemonDetailsUseCase = GetAllPokemonUseCase(PokeApiRepository())

    private val _pokemonDetailState = MutableLiveData<MutableList<Pokemon>>()
    val pokemonDetailState: LiveData<MutableList<Pokemon>> = _pokemonDetailState

    private val pokemonList = mutableListOf<Pokemon>()

    fun getAllPokemonDetails() = viewModelScope.launch {
        getAllPokemonDetailsUseCase.invoke().collect { pokemon ->
            pokemonList.add(pokemon)
            _pokemonDetailState.postValue(pokemonList)
        }
    }
}