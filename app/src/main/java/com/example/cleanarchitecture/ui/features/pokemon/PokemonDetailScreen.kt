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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.cleanarchitecture.ui.components.messageError.PokemonNotFound
import com.example.cleanarchitecture.ui.components.pokemonCard.PokemonCard
import com.example.cleanarchitecture.utils.getTypeColor
import com.example.cleanarchitecture.utils.isColorDark
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokemonDetailScreen(
    navController: NavController,
    pokemonCode: String,
    pokemonViewModel: PokemonViewModel = koinViewModel()
) {

    // Recogemos los estados del ViewModel
    val pokemonState by pokemonViewModel.pokemonState.collectAsState()
    val errorMessage by pokemonViewModel.errorMessage.collectAsState()
    val speciesState by pokemonViewModel.speciesState.collectAsState()


    val systemUiController = rememberSystemUiController()
    val typeColor = getTypeColor(pokemonState)

    // Aplicar color de status bar al detectar cambio de color
    LaunchedEffect(typeColor) {
        systemUiController.setStatusBarColor(
            color = typeColor,
            darkIcons = !isColorDark(typeColor)
        )
    }


    // Llamamos al ViewModel cuando se recibe el código
    LaunchedEffect(pokemonCode) {
        pokemonViewModel.fetchPokemon(pokemonCode)
        pokemonViewModel.fetchPokemonSpecies(pokemonCode) // Llamada para obtener la especie
    }

    // Usamos un Column para organizar los elementos de manera vertical
    Column(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            when {
                errorMessage != null -> {
                    PokemonNotFound()
                }

                pokemonState != null -> {
                    PokemonCard(pokemonState,speciesState)
                }

                else -> {
                    // Si aún no hay datos ni errores, se muestra el mensaje de carga
                    Text(
                        text = "Loading Pokemon Information",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}
