package com.example.data.network.api

import com.example.data.network.dto.PokedexDto
import retrofit2.http.GET

interface PokedexApi {
    @GET ("pokedex")
    suspend fun getPokedex(): PokedexDto
}