package com.example.cleanarchitecture.di

import com.example.domain.usecase.permission.CheckCameraPermissionUseCase
import com.example.domain.usecase.pokedex.GetPokedexUseCase
import com.example.domain.usecase.pokemon.GetPokemonByNameUseCase
import com.example.domain.usecase.pokemon.GetPokemonSpeciesByNameUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPokedexUseCase(get()) } // 👈 Registrar el caso de uso
    factory { CheckCameraPermissionUseCase(get()) }
    factory { GetPokemonByNameUseCase(get()) }
    factory { GetPokemonSpeciesByNameUseCase(get())}
}