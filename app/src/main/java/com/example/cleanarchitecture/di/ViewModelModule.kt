package com.example.cleanarchitecture.di

import com.example.cleanarchitecture.ui.features.pokemon.PokemonViewModel
import com.example.cleanarchitecture.ui.features.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{
        HomeViewModel(get() )
        PokemonViewModel(get())
    }
}