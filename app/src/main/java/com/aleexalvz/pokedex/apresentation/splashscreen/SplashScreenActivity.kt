package com.aleexalvz.pokedex.apresentation.splashscreen

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.aleexalvz.pokedex.R
import com.aleexalvz.pokedex.apresentation.pokelist.PokeListActivity
import com.aleexalvz.pokedex.base.ui.BaseActivity
import com.aleexalvz.pokedex.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : BaseActivity() {

    private val binding: ActivitySplashScreenBinding by lazy { ActivitySplashScreenBinding.inflate(layoutInflater) }
    private val viewModel: SplashScreenViewModel by lazy { ViewModelProvider(this)[SplashScreenViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        animateSplashPokeball()
    }

    private fun animateSplashPokeball() {
        val pokeballAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_pokeball_animation)

        pokeballAnimation.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) = startPokeList()
            override fun onAnimationRepeat(p0: Animation?) {}
        })

        binding.splashImagePokeball.startAnimation(pokeballAnimation)
    }

    private fun startPokeList() {
        startActivity(Intent(this, PokeListActivity::class.java))
    }
}