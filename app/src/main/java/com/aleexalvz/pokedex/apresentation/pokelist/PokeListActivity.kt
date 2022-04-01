package com.aleexalvz.pokedex.apresentation.pokelist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.aleexalvz.pokedex.R

class PokeListActivity: AppCompatActivity() {

    lateinit var viewModel: PokeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex)

        initVars()
        viewModel.getAllPokemonDetails()
    }

    private fun initVars() {
        viewModel = ViewModelProvider(this).get(PokeListViewModel::class.java)
    }

}