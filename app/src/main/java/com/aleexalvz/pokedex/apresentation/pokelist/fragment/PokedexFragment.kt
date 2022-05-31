package com.aleexalvz.pokedex.apresentation.pokelist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aleexalvz.pokedex.apresentation.pokelist.adapter.PokeListAdapter
import com.aleexalvz.pokedex.apresentation.pokelist.viewmodel.PokeListViewModel
import com.aleexalvz.pokedex.base.ui.BaseFragment
import com.aleexalvz.pokedex.data.model.ViewState
import com.aleexalvz.pokedex.databinding.FragmentPokedexBinding
import com.aleexalvz.pokedex.utils.setGone
import com.aleexalvz.pokedex.utils.setVisible

class PokedexFragment: BaseFragment() {

    private val binding: FragmentPokedexBinding by lazy {
        FragmentPokedexBinding.inflate(
            layoutInflater
        )
    }

    private val viewModel: PokeListViewModel by lazy { ViewModelProvider(requireActivity())[PokeListViewModel::class.java] }

    private val adapter: PokeListAdapter by lazy { PokeListAdapter(requireContext()) }

    private val recyclerView by lazy { binding.recyclerViewPokemons }
    private val titleContainer by lazy { binding.titleContainer }
    private val upButton by lazy { binding.upButton }
    private val progressBar by lazy { binding.progressCircular }
    private val errorContainer by lazy { binding.errorContainer }
    private val errorButtonTryAgain by lazy { binding.errorBtnTryAgain }

    private var actualYMoved = 0
    private var yTotalScrollRecyclerView = 0
    private var isTitleContainerHide = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        viewModel.getAllPokemonDetails()
        observeViewStateLiveData()
        observePokemonDetailData()
    }

    override fun onStop() {
        super.onStop()
        adapter.clearList()
        viewModel.cancelJob()
    }

    private fun observePokemonDetailData() {
        viewModel.pokemonDetailLiveData.observe(viewLifecycleOwner) {
            val newPosition = it.size - 1
            adapter.addPokemon(it[newPosition])
            adapter.notifyItemInserted(newPosition)
        }
    }

    private fun observeViewStateLiveData() {
        viewModel.viewStateLiveData.observe(viewLifecycleOwner){
            when (it){
                ViewState.Loading -> setupLoading()
                ViewState.View -> setupView()
                ViewState.Error -> setupError()
            }
        }
    }

    private fun setupError() {
        progressBar.setGone()
        recyclerView.setGone()
        errorContainer.setVisible()
    }

    private fun setupView() {
        recyclerView.setVisible()
        progressBar.setGone()
        errorContainer.setGone()
    }

    private fun setupLoading() {
        progressBar.setVisible()
        recyclerView.setGone()
        errorContainer.setGone()
    }

    private fun initViews() {
        configureListeners()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(getScrollListener())
    }

    private fun configureListeners() {
        configureUpButton()
        configureTryAgainButton()
    }

    private fun configureTryAgainButton() {
        errorButtonTryAgain.setOnClickListener {
            viewModel.cancelJob()
            viewModel.getAllPokemonDetails()
        }
    }

    private fun configureUpButton() {
        upButton.setOnClickListener {
            recyclerView.scrollToPosition(0)
            upButton.setGone()
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
            upButton.visibility = View.VISIBLE
            upButton.animate().alpha(1f).setDuration(200)
        } else upButton.animate().alpha(0f).setDuration(200).withEndAction{ upButton.visibility =
            View.INVISIBLE
        }
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