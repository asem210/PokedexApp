package com.example.cleanarchitecture.di

import com.example.cleanarchitecture.helper.NetworkChecker
import com.example.domain.usecase.pokedex.GetPokedexUseCase
import com.example.domain.usecase.pokemon.GetPokemonByNameUseCase
import com.example.domain.usecase.pokemon.GetPokemonSpeciesByNameUseCase
import com.example.domain.usecase.pokemon.LocalPokemonUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPokedexUseCase(get()) } // 👈 Registrar el caso de uso
    factory { GetPokemonByNameUseCase(get()) }
    factory { GetPokemonSpeciesByNameUseCase(get())}
    factory { LocalPokemonUseCase(get()) }

    single { NetworkChecker(get()) }// <-- Aquí declaras NetworkChecker
}