package com.aleexalvz.pokedex.apresentation.pokelist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aleexalvz.pokedex.data.model.Pokemon
import com.aleexalvz.pokedex.data.model.ViewState
import com.aleexalvz.pokedex.data.repository.PokeApiRepository
import com.aleexalvz.pokedex.data.usecase.GetAllPokemonUseCase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PokeListViewModel: ViewModel() {

    private val getAllPokemonDetailsUseCase = GetAllPokemonUseCase(PokeApiRepository())

    private val _pokemonDetailLiveData = MutableLiveData<MutableList<Pokemon>>()
    val pokemonDetailLiveData: LiveData<MutableList<Pokemon>> = _pokemonDetailLiveData

    private val _viewStateLiveData = MutableLiveData<ViewState>()
    val viewStateLiveData: LiveData<ViewState> = _viewStateLiveData

    private val pokemonList = mutableListOf<Pokemon>()

    private val job = viewModelScope.launch {
        try {
            _viewStateLiveData.postValue(ViewState.Loading)

            getAllPokemonDetailsUseCase.invoke().collect { pokemon ->

                if (viewStateLiveData.value != ViewState.View){
                    _viewStateLiveData.postValue(ViewState.View)
                }

                pokemonList.add(pokemon)
                _pokemonDetailLiveData.postValue(pokemonList)
            }

        } catch (error: Exception){
            _viewStateLiveData.postValue(ViewState.Error)
        }

    }

    fun getAllPokemonDetails() = job.start()

    fun cancelJob() {
        viewModelScope.cancel()
        job.cancel()
    }
}