package com.example.domain.repository

import com.example.domain.model.Pokemon

interface PokemonLocalRepository {
    fun insertPokemon(pokemon: Pokemon)
    fun getPokemonByName(name: String): Pokemon?
}