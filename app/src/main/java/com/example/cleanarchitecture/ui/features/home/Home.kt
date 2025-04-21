package com.example.cleanarchitecture.ui.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.ui.components.header.Header
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeComponent(navController: NavController, modifier: Modifier = Modifier) {
    val regions = listOf(
        Regions.Kanto,
        Regions.Johto,
        Regions.Hoenn,
        Regions.Sinnoh,
        Regions.Unova,
        Regions.Kalos,
        Regions.Alola
    )

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        item{
            Header(title = "Regions")
        }
        items(regions) { region ->
            RegionCard(region)
        }
    }
}

@Composable
fun RegionCard(region:Regions){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(12.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            // Fondo con imagen del banner (background image?)
            AsyncImage(
                model = region.banner,
                contentDescription = "Banner de ${region.name}",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.8f), // 🔹 Negro al 80% (arriba)
                                Color.Black.copy(alpha = 0.15f) // 🔹 Negro al 15% (abajo)
                            )                        )
                    )
            )
            Row (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                ){
                Column (
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(CardDefaults.shape),
                    verticalArrangement = Arrangement.Center
                ){
                    Text(text = region.name, fontSize = 18.sp, color = Color.White, fontFamily = FontFamily(
                        Font(R.font.poppins_semi_bold)
                    ))
                    Text(text = region.generation, fontSize = 16.sp, color = Color.LightGray, fontFamily = FontFamily(Font(R.font.poppins_medium)))
                }
                AsyncImage(
                    model = region.url,
                    contentDescription = "Pokemons Iniciales",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(52.dp)
                        .width(167.dp)
                        .align(Alignment.CenterVertically)
                        .padding(end = 16.dp) // Agrega padding a la derecha

                )
            }
        }
    }
}

sealed class Regions(val name:String, val generation:String, val banner:Int, val url: Int){
    object Kanto: Regions("Kanto", "1° Generation", R.drawable.kanto_banner, R.drawable.first_gen_init )
    object Johto: Regions("Johto", "2° Generation", R.drawable.johto_banner,R.drawable.second_gen_init)
    object Hoenn: Regions("Hoenn", "3° Generation", R.drawable.hoenn_banner,R.drawable.third_gen_init)
    object Sinnoh: Regions("Sinnoh", "4° Generation", R.drawable.sinnoh_banner, R.drawable.fourth_gen_init)
    object Unova: Regions("Unova", "5° Generation", R.drawable.unova_banner,R.drawable.fifth_gen_init)
    object Kalos: Regions("Kalos", "6° Generation", R.drawable.kalos_banner,R.drawable.sixth_gen_init)
    object Alola: Regions("Alola", "7° Generation", R.drawable.alola_banner,R.drawable.seventh_gen_init)
}