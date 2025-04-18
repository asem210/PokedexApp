package com.example.cleanarchitecture.ui.features.pokemon

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.model.Pokemon
import com.example.domain.model.PokemonSpecies
import com.example.domain.usecase.pokemon.GetPokemonByNameUseCase
import com.example.domain.usecase.pokemon.GetPokemonSpeciesByNameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val getPokemonByName: GetPokemonByNameUseCase,
    private val getPokemonSpeciesByName: GetPokemonSpeciesByNameUseCase
) : ViewModel() {

    private val _pokemonState = MutableStateFlow<Pokemon?>(null)
    val pokemonState: StateFlow<Pokemon?> = _pokemonState

    private val _speciesState = MutableStateFlow<PokemonSpecies?>(null)
    val speciesState: StateFlow<PokemonSpecies?> = _speciesState

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchPokemon(name: String) {
        viewModelScope.launch {
            try {
                val pokemon = getPokemonByName(name.lowercase())

                Log.d("PokemonViewModel", "Pokemon obtenido: $pokemon")

                _pokemonState.value = pokemon
                _errorMessage.value = null // Limpiar errores previos
            } catch (e: Exception) {
                Log.e("PokemonViewModel", "Error al obtener Pokémon: ${e.message}")

                _pokemonState.value = null
                _errorMessage.value = "No se encontró ningún Pokémon con ese nombre."
            }
        }
    }

    fun fetchPokemonSpecies(name: String) {
        viewModelScope.launch {
            try {
                val species = getPokemonSpeciesByName(name.lowercase())
                _speciesState.value = species
                Log.d("PokemonViewModel", "Especie obtenida: $species") // O cualquier propiedad relevante de species
            } catch (e: Exception) {
                _speciesState.value = null
                Log.e("PokemonViewModel", "Error al obtener species: ${e.message}")
            }
        }
    }


}