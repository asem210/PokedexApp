package com.example.cleanarchitecture.ui.components.pokemonCard

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.cleanarchitecture.utils.getTypeColor
import com.example.domain.model.Pokemon


@Composable
fun PokemonHeader(
    pokemon: Pokemon?,
    onBackClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {}
) {
    // Calcular el tamaño de la imagen basado en la altura del Pokémon
    val imageSize = pokemon?.height?.let {
        // Rango de alturas
        val minHeight = 1f  // Altura mínima (Pokémon más pequeño)
        val maxHeight = 200f // Altura máxima (Pokémon más grande)

        // Normalización de la altura
        val normalizedHeight = (it - minHeight) / (maxHeight - minHeight)

        // Escala lineal: mapea el valor de normalización entre 150.dp y 250.dp
        val scaledHeight = (normalizedHeight * 100f + 150f).dp // 150.dp para el más pequeño, 250.dp para el más grande

        // Limita el tamaño dentro del rango
        scaledHeight.coerceIn(170.dp, 230.dp)  // Aseguramos que el tamaño esté entre 150.dp y 250.dp
    } ?: 200.dp

    // Tipo de color basado en el Pokémon
    val typeColor = getTypeColor(pokemon)
    val context = LocalContext.current

    val imageLoader = remember {
        ImageLoader.Builder(context).components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }.build()
    }

    val imageUrl = pokemon?.sprites?.other?.showdown?.front_default
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        imageLoader = imageLoader
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(307.dp)
            .background(typeColor)
    ) {
        // Top Bar: Back and Favorite buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Regresar",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            IconButton(onClick = onFavoriteClick) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorito",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Centered circular image of the Pokémon (círculo blanco de 250.dp fijo)
        Box(
            modifier = Modifier
                .size(250.dp) // Aquí se establece un tamaño fijo para el círculo blanco
                .background(Color.White, shape = CircleShape)
                .align(Alignment.Center)
                .padding(4.dp)
        ) {
            imageUrl?.let {
                Image(
                    painter = painter,
                    contentDescription = "Pokemon Image",
                    modifier = Modifier
                        .size(imageSize) // El tamaño de la imagen es dinámico, dependiendo del tamaño del Pokémon
                        .align(Alignment.Center) // Esto asegura que la imagen esté centrada dentro del círculo
                )
            }
        }
    }
}