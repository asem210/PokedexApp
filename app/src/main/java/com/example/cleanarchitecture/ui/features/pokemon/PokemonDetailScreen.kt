package com.example.cleanarchitecture.ui.features.pokemon

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.cleanarchitecture.ui.components.loading.LoadingScreen
import com.example.cleanarchitecture.ui.components.messageError.PokemonNotFound
import com.example.cleanarchitecture.ui.components.network.NetworkStatusViewModel
import com.example.cleanarchitecture.ui.components.pokemonCard.PokemonCard
import com.example.cleanarchitecture.utils.getTypeColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokemonDetailScreen(
    navController: NavController,
    pokemonCode: String,
    pokemonViewModel: PokemonViewModel = koinViewModel(),
    networkStatusViewModel: NetworkStatusViewModel = koinViewModel(),
) {
    val pokemonState by pokemonViewModel.pokemonLiveData.observeAsState()
    val errorMessage by pokemonViewModel.errorMessageLiveData.observeAsState()
    val speciesState by pokemonViewModel.speciesLiveData.observeAsState()
    val isConnected by networkStatusViewModel.isConnected.collectAsState()
    val isLoading by pokemonViewModel.isLoading.observeAsState(false)

    val systemUiController = rememberSystemUiController()
    val typeColor = getTypeColor(pokemonState)

    BackHandler {
        systemUiController.setStatusBarColor(
            color = Color.Unspecified,
            darkIcons = true
        )
        navController.popBackStack()
    }

    LaunchedEffect(isLoading) {
        if (!isLoading && pokemonState != null) {
            systemUiController.setStatusBarColor(
                color = typeColor,
                darkIcons = true
            )
            systemUiController.setNavigationBarColor(
                color = Color.Transparent,
            )
        }
    }

    LaunchedEffect(pokemonCode, isConnected) {
        if (pokemonState == null) {
            pokemonViewModel.fetchPokemonData(pokemonCode)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            when {
                isLoading -> {
                    LoadingScreen()
                }
                errorMessage != null -> {
                    PokemonNotFound()
                }
                pokemonState == null && !isConnected -> {
                    Text(
                        text = "No hay conexión y no se encontró el Pokémon.",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                pokemonState != null -> {
                    PokemonCard(pokemonState, speciesState)
                }
            }
        }
    }
}
