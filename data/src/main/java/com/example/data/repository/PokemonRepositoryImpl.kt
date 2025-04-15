package com.example.data.repository

import com.example.data.network.api.PokemonApi
import com.example.domain.model.Pokemon
import com.example.domain.repository.PokemonRepository

class PokemonRepositoryImpl(private val api: PokemonApi) : PokemonRepository {
    override suspend fun getPokemonByName(name: String): Pokemon {
        return api.getPokemonByName(name).toDomain()
    }
}