package com.mirkopruiti.kotlinpokemontest.ui.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mirkopruiti.kotlinpokemontest.R
import com.mirkopruiti.kotlinpokemontest.data.model.Pokemon
import com.mirkopruiti.kotlinpokemontest.data.model.PokemonInfo
import com.mirkopruiti.kotlinpokemontest.ui.details.adapter.TypesAdapter
import com.mirkopruiti.kotlinpokemontest.ui.details.state.DetailsState
import com.mirkopruiti.kotlinpokemontest.util.GlideApp
import io.uniflow.androidx.flow.onStates
import io.uniflow.core.flow.data.UIState
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class DetailActivity : AppCompatActivity() {

    private val detailsViewModel by viewModel<DetailsViewModel>()
    private lateinit var pokemon: Pokemon
    private var adapter = TypesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        pokemon = intent.getParcelableExtra<Pokemon>("pokemon")

        setupUI()
        setupSTATE()

        lifecycleScope.launch {
            detailsViewModel.getRemotePokemonInfo(pokemon.name, this@DetailActivity)
        }

    }

    private fun setupSTATE() {
        onStates(detailsViewModel) { state ->
            when (state) {
                is UIState.Empty -> onLoading()
                is DetailsState.PokeInfo -> onSuccess(state.pokemonInfo)
                is DetailsState.Error -> onError(state.error)
            }
        }
    }

    private fun onSuccess(pokeInfo: LiveData<PokemonInfo>) {
        if (pokeInfo != null) {
            GlideApp.with(this)
                    .load(pokemon!!.getImageUrl())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .dontTransform()
                    .into(pokemonImage)

            pokeInfo.observe(this, Observer {
                pokemonName.text = it.name
                pokemonHeight.text = it.getHeightString()
                pokemonWeight.text = it.getWeightString()
                adapter.setData(it.types)
                adapter.notifyDataSetChanged()
                onSuccesData()
            })
        }
    }

    private fun onSuccesData() {
        header.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        errorView.visibility = View.GONE
    }

    private fun onError(error: String?) {
        Toast.makeText(this@DetailActivity, error, Toast.LENGTH_LONG).show()
        header.visibility = View.GONE
        progressBar.visibility = View.GONE
        errorView.visibility = View.VISIBLE
    }

    private fun onLoading() {
        header.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun setupUI() {
        rvCategory.layoutManager = GridLayoutManager(this, 2);
        rvCategory.adapter = adapter
    }

}



