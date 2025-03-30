package com.example.cleanarchitecture.di

import com.example.domain.usecase.pokedex.GetPokedexUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPokedexUseCase(get()) } // 👈 Registrar el caso de uso
}