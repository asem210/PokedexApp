package com.example.data.repository

import com.example.data.network.api.PokedexApi
import com.example.domain.model.Pokedex
import com.example.domain.repository.PokedexRepository

class PokedexRepositoryImpl (private val api: PokedexApi):PokedexRepository {
    override suspend fun getPokedex():Pokedex{
        return api.getPokedex().toDomain()
    }
}