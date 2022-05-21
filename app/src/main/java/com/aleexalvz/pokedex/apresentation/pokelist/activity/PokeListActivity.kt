package com.aleexalvz.pokedex.apresentation.pokelist.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.aleexalvz.pokedex.R
import com.aleexalvz.pokedex.apresentation.pokelist.fragment.PokedexFragment
import com.aleexalvz.pokedex.apresentation.pokelist.fragment.PokemonsFavoritosFragment
import com.aleexalvz.pokedex.base.ui.BaseActivity
import com.aleexalvz.pokedex.databinding.ActivityPokedexBinding

class PokeListActivity : BaseActivity() {

    private val binding: ActivityPokedexBinding by lazy {
        ActivityPokedexBinding.inflate(
            layoutInflater
        )
    }

    private val bottomNavigationView by lazy { binding.bottomNavigationView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupNavigation()
    }

    private fun setupNavigation() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.pokedex_fragment_container, PokedexFragment()).commit()

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_navigation_pokedex -> callPokedexFragment()
                R.id.item_navigation_favoritos -> callFavoritosFragment()
                else -> false
            }
        }
        bottomNavigationView.setOnNavigationItemReselectedListener { }
    }

    private fun callFavoritosFragment(): Boolean = replaceFragment(PokemonsFavoritosFragment())

    private fun callPokedexFragment(): Boolean = replaceFragment(PokedexFragment())

    private fun replaceFragment(fragment: Fragment): Boolean {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.enter_right_to_left,
                R.anim.exit_right_to_left,
                R.anim.enter_left_to_right,
                R.anim.exit_left_to_right
            )
            .replace(R.id.pokedex_fragment_container, fragment)
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
        return true
    }
}