package com.example.domain.repository

import com.example.domain.model.Pokemon
import com.example.domain.model.PokemonSpecies

interface PokemonLocalRepository {
    suspend fun insertPokemon(pokemon: Pokemon)
    suspend fun getPokemonByName(name: String): Pokemon?
    suspend fun insertPokemonSpecies(pokemonSpecies: PokemonSpecies)
    suspend fun getPokemonSpeciesByName(name: String): PokemonSpecies?
}