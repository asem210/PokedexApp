package com.example.cleanarchitecture

import android.app.Application
import com.example.cleanarchitecture.di.presentationModule
import com.example.cleanarchitecture.di.useCaseModule
import com.example.data.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokedexApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PokedexApp)
            modules(listOf(presentationModule, networkModule))
        }
    }
}