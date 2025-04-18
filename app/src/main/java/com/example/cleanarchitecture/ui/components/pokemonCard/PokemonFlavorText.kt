package com.example.cleanarchitecture.ui.components.pokemonCard

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.cleanarchitecture.R

@Composable
fun PokemonFlavorText(text: String) {
    Text(
        text = text,
        fontFamily = FontFamily(Font(R.font.poppins)),
        fontSize = 14.sp,
        color = Color.Black,
        modifier = Modifier.graphicsLayer(alpha = 0.7f)
    )
}