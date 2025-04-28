package com.example.cleanarchitecture.ui.components.pokemonCard

import androidx.lifecycle.ViewModel
import com.example.domain.model.PokemonSpecies

class PokemonCardViewModel : ViewModel() {
    fun formatPokemonName(name: String?): String {
        return name?.replaceFirstChar { it.uppercaseChar() } ?: "Unknown"
    }

    fun getFlavorText(speciesState: PokemonSpecies?): String {
        return speciesState?.flavorTextEntries
            ?.firstOrNull { it.language == "en" }
            ?.flavorText
            ?: "No description available"
    }

    fun formatPokemonId(id: Int?): String {
        return String.format("%03d", id ?: 0)
    }
}
