package com.aleexalvz.pokedex.apresentation.pokelist

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.aleexalvz.pokedex.R
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.onEach

class PokeListActivity: AppCompatActivity() {

    lateinit var viewModel: PokeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex)

        initVars()
        getPokemonDetailList()
    }

    private fun getPokemonDetailList() {
        viewModel.getAllPokemonDetails()
    }

    private fun initVars() {
        viewModel = ViewModelProvider(this).get(PokeListViewModel::class.java)
    }

}