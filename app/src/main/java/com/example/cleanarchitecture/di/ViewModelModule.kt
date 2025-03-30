package com.example.cleanarchitecture.di

import com.example.cleanarchitecture.ui.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{
        HomeViewModel(get() )
    }
}