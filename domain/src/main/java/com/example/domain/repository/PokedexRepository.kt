package com.example.domain.repository

import com.example.domain.model.Pokedex

interface PokedexRepository {
    suspend fun getPokedex():Pokedex
}