package com.example.cleanarchitecture.utils

import androidx.compose.runtime.Composable
import com.example.cleanarchitecture.ui.theme.bug
import com.example.cleanarchitecture.ui.theme.dark
import com.example.cleanarchitecture.ui.theme.dragon
import com.example.cleanarchitecture.ui.theme.electric
import com.example.cleanarchitecture.ui.theme.fairy
import com.example.cleanarchitecture.ui.theme.fighting
import com.example.cleanarchitecture.ui.theme.fire
import com.example.cleanarchitecture.ui.theme.flying
import com.example.cleanarchitecture.ui.theme.ghost
import com.example.cleanarchitecture.ui.theme.grass
import com.example.cleanarchitecture.ui.theme.ground
import com.example.cleanarchitecture.ui.theme.ice
import com.example.cleanarchitecture.ui.theme.normal
import com.example.cleanarchitecture.ui.theme.poison
import com.example.cleanarchitecture.ui.theme.psychic
import com.example.cleanarchitecture.ui.theme.rock
import com.example.cleanarchitecture.ui.theme.steel
import com.example.cleanarchitecture.ui.theme.water
import com.example.domain.model.Pokemon

// Función separada para obtener el color basado en el tipo de Pokémon
@Composable
fun getTypeColor(pokemonState: Pokemon?): androidx.compose.ui.graphics.Color {
    return when (pokemonState?.types?.getOrNull(0)?.type?.name?.lowercase()) {
        "water" -> water
        "dragon" -> dragon
        "electric" -> electric
        "fairy" -> fairy
        "ghost" -> ghost
        "fire" -> fire
        "ice" -> ice
        "grass" -> grass
        "bug" -> bug
        "fighting" -> fighting
        "normal" -> normal
        "dark" -> dark
        "steel" -> steel
        "rock" -> rock
        "psychic" -> psychic
        "ground" -> ground
        "poison" -> poison
        "flying" -> flying
        else -> normal // Color por defecto si no coincide
    }
}

@Composable
fun getTypeColorByName(typeName: String): androidx.compose.ui.graphics.Color {
    return when (typeName.lowercase()) {
        "water" -> water
        "dragon" -> dragon
        "electric" -> electric
        "fairy" -> fairy
        "ghost" -> ghost
        "fire" -> fire
        "ice" -> ice
        "grass" -> grass
        "bug" -> bug
        "fighting" -> fighting
        "normal" -> normal
        "dark" -> dark
        "steel" -> steel
        "rock" -> rock
        "psychic" -> psychic
        "ground" -> ground
        "poison" -> poison
        "flying" -> flying
        else -> normal // Color por defecto si no coincide
    }
}

