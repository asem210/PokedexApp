package com.example.cleanarchitecture.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.model.Pokedex
import com.example.domain.usecase.pokedex.GetPokedexUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeViewModel(private val getPokedexUseCase: GetPokedexUseCase) : ViewModel() {
    private val _pokedexState = MutableStateFlow<Pokedex?>(null)
    val pokedexState: StateFlow<Pokedex?> = _pokedexState

    init {
        fetchPokedex()
    }

    private fun fetchPokedex() {
        viewModelScope.launch {
            try {
                val pokedex = withContext(Dispatchers.IO) {
                    getPokedexUseCase()
                }
                Log.d("HomeViewModel", "Fetched Pokedex: $pokedex")
                _pokedexState.value = pokedex
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error fetching Pokedex", e)
            }
        }
    }
}