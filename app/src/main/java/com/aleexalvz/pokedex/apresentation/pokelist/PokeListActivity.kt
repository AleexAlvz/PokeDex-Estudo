package com.aleexalvz.pokedex.apresentation.pokelist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aleexalvz.pokedex.databinding.ActivityPokedexBinding

class PokeListActivity: AppCompatActivity() {

    private val viewModel: PokeListViewModel by lazy { ViewModelProvider(this)[PokeListViewModel::class.java] }
    private val binding: ActivityPokedexBinding by lazy { ActivityPokedexBinding.inflate(layoutInflater) }
    private val adapter: PokeListAdapter by lazy { PokeListAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
        viewModel.getAllPokemonDetails()
        observeActivityState()
    }

    private fun observeActivityState() {
        viewModel.pokemonDetailState.observe(this) {
            adapter.addPokemon(it[it.size-1])
            adapter.notifyDataSetChanged()
        }
    }

    private fun initViews() {
        binding.recyclerViewPokemons.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewPokemons.adapter = adapter
    }
}