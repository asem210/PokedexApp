package com.example.domain.model


data class PokemonSpecies(
    val name: String,
    val flavorTextEntries: List<FlavorTextEntry>,
    val gender_rate: Int,
    val genera: List<Genus>
)

data class Genus(
    val genus: String,
    val language: String
)
data class FlavorTextEntry(
    val flavorText: String,
    val language: String,
    val version: String
)

