package com.mirkopruiti.kotlinpokemontest.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mirkopruiti.kotlinpokemontest.R
import com.mirkopruiti.kotlinpokemontest.data.model.Pokemon
import com.mirkopruiti.kotlinpokemontest.util.GlideApp

class HomeAdapter(private val pokemonClickListener: PokemonClickListener) : PagingDataAdapter<Pokemon, PokemonViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bindTo(getItem(position), holder)
        holder.itemView.setOnClickListener {
            getItem(position)?.let { it -> pokemonClickListener.onPokemonClickListener(it) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonViewHolder {
        return PokemonViewHolder(parent)
    }

}


class PokemonViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.home_item, parent, false)) {

    private val pokemonImage = itemView.findViewById<ImageView>(R.id.pokemonImage)
    private val pokemonName = itemView.findViewById<TextView>(R.id.pokemonName)

    fun bindTo(poke: Pokemon?, holder: PokemonViewHolder) {

        val pokemon = poke

        GlideApp.with(holder.itemView?.context)
            .load(pokemon!!.getImageUrl())
            .transition(DrawableTransitionOptions.withCrossFade())
            .dontTransform()
            .into(pokemonImage)

        pokemonName.text = pokemon!!.name

    }
}