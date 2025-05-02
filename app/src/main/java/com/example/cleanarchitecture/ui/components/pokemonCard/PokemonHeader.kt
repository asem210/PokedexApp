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
import com.example.cleanarchitecture.ui.components.header.TopBar
import com.example.cleanarchitecture.utils.getTypeColor
import com.example.domain.model.Pokemon


@Composable
fun PokemonHeader(
    pokemon: Pokemon?,
    onBackClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {}
) {
    val imageSize = pokemon?.height?.let {
        val minHeight = 1f
        val maxHeight = 200f

        val normalizedHeight = (it - minHeight) / (maxHeight - minHeight)

        val scaledHeight = (normalizedHeight * 100f + 150f).dp
        scaledHeight.coerceIn(170.dp, 230.dp)
    } ?: 200.dp

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
            .height(300.dp)
            .background(typeColor)
    ) {
        TopBar(
            onFavoriteClick = { /* Acción para el favorito */ },
            showFavoriteButton = true,
            pokemon = pokemon
        )

        Box(
            modifier = Modifier
                .size(250.dp)
                .background(Color.White, shape = CircleShape)
                .align(Alignment.Center)
                .padding(4.dp)
        ) {
            imageUrl?.let {
                Image(
                    painter = painter,
                    contentDescription = "Pokemon Image",
                    modifier = Modifier
                        .size(imageSize)
                        .align(Alignment.Center)
                )
            }
        }
    }
}