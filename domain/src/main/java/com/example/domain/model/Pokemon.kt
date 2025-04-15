package com.example.domain.model

data class Pokemon(
    val id: Int,
    val name: String,
    val base_experience: Int,
    val height: Int,
    val weight: Int,
    val order: Int,
    val abilities: List<AbilitySlot>,
    val types: List<TypeSlot>,
    val sprites: Sprites,
    val stats: List<Stats>
)

data class AbilitySlot(
    val is_hidden: Boolean,
    val slot: Int,
    val ability: NamedApiResource
)

data class TypeSlot(
    val slot: Int,
    val type: NamedApiResource
)

data class Sprites(
    val front_default: String?,
    val back_default: String?,
    val other: Other?,
)

data class Other (
    val showdown: Showdown?
)

data class Showdown(
    val front_default: String?,
    val back_default: String?
)

data class Stats(
    val base_stat: Int,
    val effort: Int,
    val stat: NamedApiResource
)

data class NamedApiResource(
    val name: String,
    val url: String
)

