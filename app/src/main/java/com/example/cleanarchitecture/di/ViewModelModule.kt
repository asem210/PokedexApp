package com.example.cleanarchitecture.di

import com.example.cleanarchitecture.ui.components.network.NetworkStatusViewModel
import com.example.cleanarchitecture.ui.features.pokemon.PokemonViewModel
import com.example.cleanarchitecture.ui.features.home.HomeViewModel
import com.example.cleanarchitecture.ui.features.login.viewmodel.LoginViewModel
import com.example.cleanarchitecture.ui.components.pokemonCard.PokemonCardViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { PokemonViewModel(get(), get(),get(),get()) }
    viewModel { LoginViewModel(FirebaseAuth.getInstance()) }
    viewModel { PokemonCardViewModel() }
    viewModel { NetworkStatusViewModel(get()) }
}