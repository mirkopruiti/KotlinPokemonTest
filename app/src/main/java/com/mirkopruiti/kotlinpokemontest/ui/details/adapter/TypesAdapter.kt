package com.mirkopruiti.kotlinpokemontest.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mirkopruiti.kotlinpokemontest.R
import com.mirkopruiti.kotlinpokemontest.data.model.PokemonInfo


class TypesAdapter : RecyclerView.Adapter<TypesAdapter.TypeViewHolder>() {

    private var list: List<PokemonInfo.TypeResponse> = emptyList()

    fun setData(types: List<PokemonInfo.TypeResponse>) {
        list = types
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TypeViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        val type: PokemonInfo.Type = list[position].type
        holder.bind(type)
    }

    override fun getItemCount(): Int = list.size

    inner class TypeViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.types_item, parent, false)) {
        private var pokemonType: TextView? = null

        init {
            pokemonType = itemView.findViewById(R.id.pokemonType)
        }

        fun bind(type: PokemonInfo.Type) {
            pokemonType?.text = type.name
        }

    }

}