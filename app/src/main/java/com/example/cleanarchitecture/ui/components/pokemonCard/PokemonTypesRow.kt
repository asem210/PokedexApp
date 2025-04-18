package com.example.cleanarchitecture.ui.components.pokemonCard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.example.cleanarchitecture.utils.getTypeColorByName
import com.example.domain.model.Pokemon
import com.example.domain.model.TypeSlot

@Composable
fun PokemonTypesRow(types: List<TypeSlot>) {
    Row {
        types.forEach { typeSlot ->
            val typeName = typeSlot.type.name.replaceFirstChar { it.uppercase() }
            val backgroundColor = getTypeColorByName(typeSlot.type.name)

            Box(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .background(backgroundColor, shape = CircleShape)
                    .padding(horizontal = 12.dp, vertical = 4.dp)
                    .height(36.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .background(Color.White, shape = CircleShape)
                    )
                    Text(
                        text = typeName,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 14.sp,
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}
