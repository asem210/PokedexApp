package com.example.cleanarchitecture.ui.components.messageError

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cleanarchitecture.R

@Composable
fun PokemonNotFound() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.error_image),
                contentDescription = "Pokemon no encontrado",
                modifier = Modifier
                    .width(185.dp)
                    .height(214.dp)
            )
            Text(
                text = "Pokemon not found :( ",
                fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                fontSize = 20.sp,
                color = Color(0xFF333333),
                modifier = Modifier.padding(top = 12.dp)
            )
            Text(
                text = "The scanned code is not supported. Please use a valid QR code.",
                fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                fontSize = 14.sp,
                color = Color(0xFF4D4D4D),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp, start = 24.dp, end = 24.dp)
            )
        }
    }
}
