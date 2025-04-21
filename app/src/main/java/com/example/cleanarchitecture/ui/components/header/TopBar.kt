package com.example.cleanarchitecture.ui.components.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cleanarchitecture.R

@Composable
fun TopBar(
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    showFavoriteButton: Boolean = true,
    title: String? = null
) {
    androidx.compose.foundation.layout.Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        val backIconColor = if (title != null) Color.Black else Color.White

        IconButton(
            onClick = onBackClick,
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
            IconButton(
                onClick = onFavoriteClick,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorito",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

