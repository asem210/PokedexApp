package com.example.cleanarchitecture.ui.components.pokemonCard

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.domain.model.Pokemon

@Composable
fun PokemonCard(pokemonState:Pokemon?){
    Column {
        PokemonHeader(pokemonState)
    }
}