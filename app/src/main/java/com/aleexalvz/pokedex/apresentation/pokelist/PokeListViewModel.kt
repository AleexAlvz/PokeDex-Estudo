package com.aleexalvz.pokedex.apresentation.pokelist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aleexalvz.pokedex.data.model.PokemonDetail
import com.aleexalvz.pokedex.data.repository.PokeApiRepository
import com.aleexalvz.pokedex.data.usecase.GetAllPokemonDetailsUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PokeListViewModel: ViewModel() {

    private val getAllPokemonDetailsUseCase = GetAllPokemonDetailsUseCase(PokeApiRepository())

    private val _pokemonDetailState = MutableLiveData<MutableList<PokemonDetail>>()
    val pokemonDetailState: LiveData<MutableList<PokemonDetail>> = _pokemonDetailState

    private val pokemonDetailList = mutableListOf<PokemonDetail>()

    fun getAllPokemonDetails() = viewModelScope.launch {
        getAllPokemonDetailsUseCase.invoke().collect { pokemon ->
            pokemonDetailList.add(pokemon)
            _pokemonDetailState.postValue(pokemonDetailList)
        }
    }
}