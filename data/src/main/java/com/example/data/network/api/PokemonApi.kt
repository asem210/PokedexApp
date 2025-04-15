package com.example.data.network.api

import com.example.data.network.dto.PokemonDto
import retrofit2.http.GET
import retrofit2.http.Path


interface PokemonApi {
    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String): PokemonDto
}