package com.aleexalvz.pokedex.apresentation.pokelist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aleexalvz.pokedex.data.model.PokemonDetail
import com.aleexalvz.pokedex.data.repository.PokeApiRepository
import com.aleexalvz.pokedex.data.usecase.PokemonUsecases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PokeListViewModel: ViewModel() {

    private val pokemonUsecases = PokemonUsecases(PokeApiRepository())

    private val _pokemonDetailList = MutableLiveData<List<PokemonDetail>>()
    val pokemonDetailList: LiveData<List<PokemonDetail>> = _pokemonDetailList

    fun getAllPokemonDetails(){
        CoroutineScope(IO).launch{
            var count = 1
            pokemonUsecases.getAllPokemonDetails().collect {
                Log.i("POKEMON", "Pokemon name: ${it.forms[0].name}, index: $count")
                count++
            }
        }
    }
}