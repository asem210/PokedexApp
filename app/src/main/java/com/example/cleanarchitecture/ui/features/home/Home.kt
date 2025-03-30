package com.example.cleanarchitecture.ui.features.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeComponent(navController: NavController, modifier: Modifier = Modifier){
    val homeViewModel: HomeViewModel = koinViewModel() // 👈 Inyectar HomeViewModel
    val pokedexState by homeViewModel.pokedexState.collectAsState()

    Text(
        text = "Fetched Pokedex: ${pokedexState ?: "Loading..."}"
    )
}