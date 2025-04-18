package com.example.cleanarchitecture.ui.components.pokemonCard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.ui.theme.female
import com.example.cleanarchitecture.ui.theme.fontColorDefault
import com.example.cleanarchitecture.ui.theme.male

@Composable
fun GenderRateBar(genderRate: Int?, modifier: Modifier = Modifier) {
    val femaleRatio = if (genderRate in 0..8) genderRate!! / 8f else 0f
    val maleRatio = 1f - femaleRatio

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título "Gender"
        Text(
            text = "Gender",
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            fontSize = 14.sp,
            color = fontColorDefault,
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Barra principal
        Box(
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(50))
                .background(Color.LightGray)
        ) {
            if (genderRate == -1) {
                // Genderless centrado
                Text(
                    text = "Genderless",
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    modifier = Modifier.align(Alignment.Center),
                    color = fontColorDefault,
                    fontSize = 12.sp
                )
            } else {
                // Parte femenina
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(femaleRatio)
                        .background(female)
                )

                // Parte masculina
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(maleRatio)
                        .align(Alignment.CenterEnd)
                        .background(male)
                )
            }
        }

        // Porcentajes debajo de la barra (si tiene género)
        if (genderRate != -1) {
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "♀ ${(femaleRatio * 100).toInt()}%",
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    color = fontColorDefault,
                    fontSize = 12.sp,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "♂ ${(maleRatio * 100).toInt()}%",
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    color = fontColorDefault,
                    fontSize = 12.sp,
                    textAlign = androidx.compose.ui.text.style.TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}





