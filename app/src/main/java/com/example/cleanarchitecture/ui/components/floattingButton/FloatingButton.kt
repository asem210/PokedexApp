package com.example.cleanarchitecture.ui.components.floattingButton

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cleanarchitecture.R

@Composable
fun FloatingButton(onClick: () -> Unit) {
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = tween(durationMillis = 150)
    )

    FloatingActionButton(
        onClick = onClick,
        shape = CircleShape,
        containerColor = Color(0xFFFFEFDB), // Gris azul muy sobrio
        contentColor = Color.White,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 6.dp,
            pressedElevation = 10.dp
        ),
        modifier = Modifier
            .size(56.dp)
            .scale(scale)
            .shadow(6.dp, CircleShape)
    ) {
        Image(
            painter = painterResource(id = R.drawable.qr_code),
            contentDescription = "Escanear QR",
            modifier = Modifier.size(28.dp) // Icono más pequeño, elegante
        )
    }
}
