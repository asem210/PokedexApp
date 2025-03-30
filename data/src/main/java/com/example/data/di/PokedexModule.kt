package com.example.data.di

import com.example.data.network.api.PokedexApi
import com.example.data.repository.PokedexRepositoryImpl
import com.example.domain.repository.PokedexRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val PokedexModule = module {
    single{
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokedexApi::class.java)
    }
    single <PokedexRepository> {PokedexRepositoryImpl(get())}
}