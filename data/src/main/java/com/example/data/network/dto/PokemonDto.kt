package com.example.data.network.dto

import com.google.gson.annotations.SerializedName
import com.example.domain.model.*


data class PokemonDto(
    val id: Int,
    val name: String,
    @SerializedName("base_experience") val baseExperience: Int,
    val height: Int,
    val weight: Int,
    val order: Int,
    val abilities: List<AbilitySlotDto>,
    val types: List<TypeSlotDto>,
    val sprites: SpritesDto,
    val stats: List<StatsDto>
) {
    fun toDomain(): Pokemon {
        return Pokemon(
            id = id,
            name = name,
            base_experience = baseExperience,
            height = height,
            weight = weight,
            order= order,
            abilities = abilities.map { it.toDomain() },
            types = types.map { it.toDomain() },
            sprites = sprites.toDomain(),
            stats = stats.map { it.toDomain() }
        )
    }
}

data class AbilitySlotDto(
    @SerializedName("is_hidden") val isHidden: Boolean,
    val slot: Int,
    val ability: NamedApiResourceDto
) {
    fun toDomain(): AbilitySlot {
        return AbilitySlot(
            is_hidden = isHidden,
            slot = slot,
            ability = ability.toDomain()
        )
    }
}

data class TypeSlotDto(
    val slot: Int,
    val type: NamedApiResourceDto
) {
    fun toDomain(): TypeSlot {
        return TypeSlot(
            slot = slot,
            type = type.toDomain()
        )
    }
}

data class SpritesDto(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("back_default") val backDefault: String?,
    val other: OtherDto?
) {
    fun toDomain(): Sprites {
        return Sprites(
            front_default = frontDefault,
            back_default = backDefault,
            other = other?.toDomain()
        )
    }
}

data class OtherDto(
    val showdown: ShowdownDto?
) {
    fun toDomain(): Other {
        return Other(
            showdown = showdown?.toDomain()
        )
    }
}

data class ShowdownDto(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("back_default") val backDefault: String?
) {
    fun toDomain(): Showdown {
        return Showdown(
            front_default = frontDefault,
            back_default = backDefault
        )
    }
}


data class StatsDto(
    @SerializedName("base_stat") val baseStat: Int,
    val effort: Int,
    val stat: NamedApiResourceDto
) {
    fun toDomain(): Stats {
        return Stats(
            base_stat = baseStat,
            effort = effort,
            stat = stat.toDomain()
        )
    }
}

data class NamedApiResourceDto(
    val name: String,
    val url: String
) {
    fun toDomain(): NamedApiResource {
        return NamedApiResource(
            name = name,
            url = url
        )
    }
}