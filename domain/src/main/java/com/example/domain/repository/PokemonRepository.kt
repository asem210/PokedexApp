package com.example.domain.repository

import com.example.domain.model.Pokedex
import com.example.domain.model.Pokemon
import com.example.domain.model.PokemonSpecies

interface PokemonRepository {
    suspend fun getPokemonByName(name: String): Pokemon

    suspend fun getPokemonSpeciesByName(name: String):PokemonSpecies
}