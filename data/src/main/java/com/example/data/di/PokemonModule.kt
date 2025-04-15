package com.example.data.di


import com.example.data.network.api.PokemonApi
import com.example.data.repository.PokemonRepositoryImpl
import com.example.domain.repository.PokemonRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val PokemonModule = module {
    // Aquí creas una sola instancia de Retrofit para PokemonApi
    single {
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }

    // Luego, registras el repositorio de Pokemon
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
}
