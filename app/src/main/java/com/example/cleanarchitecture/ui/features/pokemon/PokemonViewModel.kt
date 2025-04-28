package com.example.cleanarchitecture.ui.features.pokemon

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cleanarchitecture.helper.NetworkChecker
import com.example.domain.model.Pokemon
import com.example.domain.model.PokemonSpecies
import com.example.domain.usecase.pokemon.GetPokemonByNameUseCase
import com.example.domain.usecase.pokemon.GetPokemonSpeciesByNameUseCase
import com.example.domain.usecase.pokemon.LocalPokemonUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonViewModel(
    private val getPokemonByName: GetPokemonByNameUseCase,
    private val getPokemonSpeciesByName: GetPokemonSpeciesByNameUseCase,
    private val localPokemonUseCase: LocalPokemonUseCase,
    private val networkChecker: NetworkChecker
) : ViewModel() {

    private val _pokemonLiveData = MutableLiveData<Pokemon?>()
    val pokemonLiveData: LiveData<Pokemon?> = _pokemonLiveData

    private val _speciesLiveData = MutableLiveData<PokemonSpecies?>()
    val speciesLiveData: LiveData<PokemonSpecies?> = _speciesLiveData

    private val _errorMessageLiveData = MutableLiveData<String?>()
    val errorMessageLiveData: LiveData<String?> = _errorMessageLiveData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private suspend fun isNetworkAvailable(): Boolean {
        return withContext(Dispatchers.IO) { networkChecker.isNetworkAvailable() }
    }

    suspend fun fetchPokemon(name: String) {
        try {
            val nameLower = name.lowercase()
            if (isNetworkAvailable()) {
                val pokemon = getPokemonByName(nameLower)
                _pokemonLiveData.postValue(pokemon)
                _errorMessageLiveData.postValue(null)
                localPokemonUseCase.savePokemon(pokemon)
            } else {
                val savedPokemon = localPokemonUseCase.getSavedPokemon(nameLower)
                _pokemonLiveData.postValue(savedPokemon)
                _errorMessageLiveData.postValue(null)
            }
        } catch (e: Exception) {
            _pokemonLiveData.postValue(null)
            _errorMessageLiveData.postValue("No se encontró ningún Pokémon con ese nombre.")
        }
    }

    suspend fun fetchPokemonSpecies(name: String) {
        try {
            val nameLower = name.lowercase()
            if (isNetworkAvailable()) {
                val species = getPokemonSpeciesByName(nameLower)
                _speciesLiveData.postValue(species)
                localPokemonUseCase.savePokemonSpecies(species)
            } else {
                val savedSpecies = localPokemonUseCase.getSavedPokemonSpecie(nameLower)
                _speciesLiveData.postValue(savedSpecies)
            }
        } catch (e: Exception) {
            _speciesLiveData.postValue(null)
        }
    }

    fun fetchPokemonData(name: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                fetchPokemon(name)
                fetchPokemonSpecies(name)
            } finally {
                delay(2000)
                _isLoading.value = false
            }
        }
    }
}
