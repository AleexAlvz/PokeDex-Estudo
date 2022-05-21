package com.aleexalvz.pokedex.apresentation.pokelist.activity

import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aleexalvz.pokedex.R
import com.aleexalvz.pokedex.apresentation.pokelist.adapter.PokeListAdapter
import com.aleexalvz.pokedex.apresentation.pokelist.fragment.PokedexFragment
import com.aleexalvz.pokedex.apresentation.pokelist.viewmodel.PokeListViewModel
import com.aleexalvz.pokedex.base.ui.BaseActivity
import com.aleexalvz.pokedex.databinding.ActivityPokedexBinding

class PokeListActivity : BaseActivity() {


    private val binding: ActivityPokedexBinding by lazy {
        ActivityPokedexBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupNavigation()
    }

    private fun setupNavigation() {
        supportFragmentManager.beginTransaction().replace(R.id.pokedex_fragment_container, PokedexFragment()).commit()


    }
}