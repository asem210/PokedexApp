package com.example.cleanarchitecture.di

// En tu módulo Koin
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val DependenciesModule = module {
    // Define FirebaseAuth como una dependencia singleton
    single { FirebaseAuth.getInstance() }
}
