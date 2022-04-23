package com.aleexalvz.pokedex.apresentation.pokelist

import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aleexalvz.pokedex.base.ui.BaseActivity
import com.aleexalvz.pokedex.databinding.ActivityPokedexBinding

class PokeListActivity : BaseActivity() {

    private val viewModel: PokeListViewModel by lazy { ViewModelProvider(this)[PokeListViewModel::class.java] }
    private val binding: ActivityPokedexBinding by lazy {
        ActivityPokedexBinding.inflate(
            layoutInflater
        )
    }
    private val adapter: PokeListAdapter by lazy { PokeListAdapter(this) }

    private val recyclerView by lazy { binding.recyclerViewPokemons }
    private val titleContainer by lazy { binding.titleContainer }
    private val upButton by lazy { binding.upButton }

    private var actualYMoved = 0
    private var yTotalScrollRecyclerView = 0
    private var isTitleContainerHide = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
        viewModel.getAllPokemonDetails()
        observeActivityState()
    }

    private fun observeActivityState() {
        viewModel.pokemonDetailState.observe(this) {
            val newPosition = it.size - 1
            adapter.addPokemon(it[newPosition])
            adapter.notifyItemInserted(newPosition)
        }
    }

    private fun initViews() {
        configureListeners()
        recyclerView.layoutManager = LinearLayoutManager(this@PokeListActivity)
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(getScrollListener())
    }

    private fun configureListeners() {
        configureUpButton()
    }

    private fun configureUpButton() {
        upButton.setOnClickListener {
            recyclerView.scrollToPosition(0)
            upButton.visibility = INVISIBLE
            yTotalScrollRecyclerView = 0
            actualYMoved = 0
            verifyUpButtonVisibility()
            showTitleContainer()
        }
    }

    private fun getScrollListener() = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            updatePositionVars(dy)

            if (actualYMoved < -5000 && isTitleContainerHide) showTitleContainer()
            else if (actualYMoved > 5000 && !isTitleContainerHide) hideTitleContainer()

            verifyUpButtonVisibility()
        }
    }

    private fun updatePositionVars(dy: Int) {
        yTotalScrollRecyclerView += dy
        actualYMoved += dy

        if (isTitleContainerHide && dy < 0) actualYMoved += dy
        else if (!isTitleContainerHide && dy > 0) actualYMoved += dy
        else actualYMoved = 0
    }

    private fun verifyUpButtonVisibility() {
        if (yTotalScrollRecyclerView > 500) {
            upButton.visibility = VISIBLE
            upButton.animate().alpha(1f).setDuration(200)
        } else upButton.animate().alpha(0f).setDuration(200).withEndAction{ upButton.visibility = INVISIBLE }
    }

    private fun hideTitleContainer() {
        isTitleContainerHide = true
        val titleContainerHeight = binding.titleContainer.height
        titleContainer.animate().translationY(-titleContainerHeight.toFloat())
            .alpha(0f).duration = 500
        recyclerView.animate()
            .translationY(-titleContainerHeight.toFloat() * 0.9f).duration = 500
        actualYMoved = 0
    }

    private fun showTitleContainer() {
        isTitleContainerHide = false
        titleContainer.animate().translationY(0f).alpha(1f).duration = 500
        recyclerView.animate().translationY(0f).duration = 500
        actualYMoved = 0
    }
}