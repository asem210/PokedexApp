package com.example.cleanarchitecture.ui.components.header

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.ui.features.favorites.FavoriteViewModel
import com.example.cleanarchitecture.ui.features.login.viewmodel.LoginViewModel
import com.example.domain.model.Pokemon
import org.koin.androidx.compose.koinViewModel

@Composable
fun TopBar(
    onFavoriteClick: () -> Unit,
    showFavoriteButton: Boolean = true,
    title: String? = null,
    pokemon: Pokemon? = null,
    loginViewModel: LoginViewModel = koinViewModel(),
    favoriteViewModel: FavoriteViewModel = viewModel()
) {
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    val userState by loginViewModel.loginResult.collectAsState()
    val userId = userState?.uid.orEmpty()

    // Usamos isFavorite directamente sin observar LiveData
    val isFavorite = favoriteViewModel.isFavorite

    // Llama solo si hay usuario y Pokémon
    LaunchedEffect(userId, pokemon?.id) {
        if (userId.isNotEmpty() && pokemon != null) {
            favoriteViewModel.checkIfFavorite(userId, pokemon.id)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        val backIconColor = if (title != null) Color.Black else Color.White

        IconButton(
            onClick = { backDispatcher?.onBackPressed() },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Regresar",
                tint = backIconColor,
                modifier = Modifier.size(32.dp)
            )
        }

        title?.let {
            Text(
                text = it,
                fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        if (title == null && showFavoriteButton) {
            // Solo muestra el ícono de favorito si tenemos el estado actualizado
            if (isFavorite != null) {
                IconButton(
                    onClick = {
                        if (userId.isNotEmpty() && pokemon != null) {
                            if (isFavorite == true) {
                                favoriteViewModel.removeFavorite(userId, pokemon) // Eliminar de favoritos
                            } else {
                                favoriteViewModel.addFavorite(userId, pokemon) // Agregar a favoritos
                            }
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Icon(
                        imageVector = if (isFavorite == true) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (isFavorite == true) "Eliminar de favoritos" else "Agregar a favoritos",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}


