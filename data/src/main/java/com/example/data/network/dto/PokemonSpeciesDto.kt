package com.example.data.network.dto

import com.example.domain.model.FlavorTextEntry
import com.example.domain.model.Genus
import com.example.domain.model.Pokemon
import com.example.domain.model.PokemonSpecies

data class PokemonSpeciesDto(
    val name: String,
    val flavor_text_entries: List<FlavorTextEntryDto>,
    val genera: List<GenusDto>,
    val gender_rate: Int

) {
    fun toDomain(): PokemonSpecies {
        val entries = flavor_text_entries.map {
            FlavorTextEntry(
                flavorText = it.flavor_text.replace("\n", " ").replace("\u000c", " ").trim(),
                language = it.language.name,
                version = it.version.name
            )
        }

        val generaDomain = genera.map {
            Genus(
                genus = it.genus,
                language = it.language.name
            )
        }


        return PokemonSpecies(
            name = name,
            flavorTextEntries = entries,
            genera = generaDomain,
            gender_rate = gender_rate
        )
    }
}

data class FlavorTextEntryDto(
    val flavor_text: String,
    val language: LanguageDto,
    val version: VersionDto
)

data class GenusDto(
    val genus: String,
    val language: LanguageDto
)

data class LanguageDto(val name: String)
data class VersionDto(val name: String)