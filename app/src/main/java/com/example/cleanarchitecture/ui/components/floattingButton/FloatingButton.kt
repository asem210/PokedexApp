package com.example.cleanarchitecture.ui.components.floattingButton

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cleanarchitecture.R

@Composable
fun FloatingButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        shape = CircleShape, // 🔹 Forma circular
        containerColor = Color(0xFF2196F3), // 🔹 Azul primario
        contentColor = Color.White,
        elevation = FloatingActionButtonDefaults.elevation(8.dp), // 🔹 Sombra
        modifier = Modifier
            .size(64.dp) // 🔹 Tamaño personalizado
            .shadow(10.dp, CircleShape) // 🔹 Sombra extra
    ) {
        Image(
            painter = painterResource(id = R.drawable.qr_code),
            contentDescription = "Escanear QR",
            modifier = Modifier.size(32.dp)
        )
    }
}