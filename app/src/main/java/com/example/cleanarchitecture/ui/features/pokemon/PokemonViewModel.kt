package com.example.cleanarchitecture.ui.features.pokemon

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Pokemon
import com.example.domain.usecase.pokemon.GetPokemonByNameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val getPokemonByName: GetPokemonByNameUseCase
) : ViewModel() {

    // Estado para contener el Pokémon
    private val _pokemonState = MutableStateFlow<Pokemon?>(null)
    val pokemonState: StateFlow<Pokemon?> = _pokemonState

    // Estado para manejar errores
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    // Función para obtener el Pokémon usando el caso de uso
    fun fetchPokemon(name: String) {
        viewModelScope.launch {
            try {
                // Obtén el Pokémon y guarda el resultado
                val pokemon = getPokemonByName(name.lowercase())

                // Imprime el resultado en el Logcat
                Log.d("PokemonViewModel", "Pokemon obtenido: $pokemon")

                // Actualiza el estado con el Pokémon
                _pokemonState.value = pokemon
                _errorMessage.value = null // Limpiar errores previos
            } catch (e: Exception) {
                // En caso de que ocurra un error, lo logueamos
                Log.e("PokemonViewModel", "Error al obtener Pokémon: ${e.message}")

                // Limpiamos el estado del Pokémon anterior (si existía) y mostramos mensaje de error
                _pokemonState.value = null
                _errorMessage.value = "No se encontró ningún Pokémon con ese nombre."
            }
        }
    }
}