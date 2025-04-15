package com.example.domain.repository

import com.example.domain.model.Pokedex
import com.example.domain.model.Pokemon

interface PokemonRepository {
    suspend fun getPokemonByName(name: String): Pokemon
}