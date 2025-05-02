package com.example.data.di

import com.example.data.local.repository.PokemonLocalRepositoryImpl
import com.example.data.network.api.PokedexApi
import com.example.data.network.api.PokemonApi
import com.example.data.repository.PokedexRepositoryImpl
import com.example.data.repository.PokemonRepositoryImpl
import com.example.domain.repository.PokedexRepository
import com.example.domain.repository.PokemonLocalRepository
import com.example.domain.repository.PokemonRepository
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(PokedexApi::class.java) }
    single { get<Retrofit>().create(PokemonApi::class.java) }
    single<PokedexRepository> { PokedexRepositoryImpl(get()) }
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
    single<PokemonLocalRepository> { PokemonLocalRepositoryImpl(get()) }

}
