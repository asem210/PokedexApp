package com.example.cleanarchitecture.utils

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.ColorUtils

// Utilidad para verificar si el color es oscuro (para decidir si los íconos son claros u oscuros)
fun isColorDark(color: Color): Boolean {
    val luminance = ColorUtils.calculateLuminance(
        android.graphics.Color.argb(
            (color.alpha * 255).toInt(),
            (color.red * 255).toInt(),
            (color.green * 255).toInt(),
            (color.blue * 255).toInt()
        )
    )
    return luminance < 0.5
}
