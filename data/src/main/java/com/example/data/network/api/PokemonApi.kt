package com.example.data.network.api

import com.example.data.network.dto.PokemonDto
import com.example.data.network.dto.PokemonSpeciesDto
import retrofit2.http.GET
import retrofit2.http.Path


interface PokemonApi {
    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String): PokemonDto

    @GET("pokemon-species/{name}")
    suspend fun getPokemonSpeciesByName(@Path("name") name: String): PokemonSpeciesDto
}