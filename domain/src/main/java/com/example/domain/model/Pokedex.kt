package com.example.domain.model

import java.net.URL

data class Pokedex(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokedexEntry>
)

data class PokedexEntry(
    val name: String,
    val url: String
)
