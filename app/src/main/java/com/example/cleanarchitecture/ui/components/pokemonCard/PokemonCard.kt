package com.example.cleanarchitecture.ui.components.pokemonCard

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.ui.viewModel.pokemon.PokemonCardViewModel
import com.example.cleanarchitecture.utils.getTypeColorByName
import com.example.domain.model.Pokemon
import com.example.domain.model.PokemonSpecies

@Composable
fun PokemonCard(pokemonState: Pokemon?, speciesState: PokemonSpecies?) {
    val cardviewModel: PokemonCardViewModel = viewModel()

    val genusInEnglish: String? = speciesState?.genera
        ?.firstOrNull { it.language == "en" }
        ?.genus
        ?.replace("Pokémon", "", ignoreCase = true)
        ?.trim()

    val firstAbility = pokemonState?.abilities?.firstOrNull()?.ability?.name.orEmpty().replaceFirstChar { it.uppercase() }

    Column {
        PokemonHeader(pokemonState)

        AnimatedVisibility(
            visible = pokemonState != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            ) {
                LazyColumn {
                    item {
                        PokemonNameAndId(
                            name = cardviewModel.formatPokemonName(pokemonState?.name),
                            id = pokemonState?.id ?: 0
                        )
                    }

                    item { Spacer(modifier = Modifier.height(8.dp)) }

                    pokemonState?.types?.let { types ->
                        item {
                            PokemonTypesRow(types = types)
                        }
                    }

                    item { Spacer(modifier = Modifier.height(12.dp)) }

                    item {
                        PokemonFlavorText(cardviewModel.getFlavorText(speciesState))
                    }

                    item { Spacer(modifier = Modifier.height(8.dp)) }

                    item {
                        PokemonInfo(
                            height = pokemonState?.height,
                            weight = pokemonState?.weight,
                            category = genusInEnglish.orEmpty(),
                            ability = firstAbility
                        )
                    }

                    item{
                        GenderRateBar(genderRate = speciesState?.gender_rate, modifier = Modifier.padding(8.dp))
                    }

                }
            }
        }
    }
}