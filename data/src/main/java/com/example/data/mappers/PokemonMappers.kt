package com.example.data.mappers

import com.example.domain.model.Pokemon

fun Pokemon.toFavoriteMap(): Map<String, Any?> {
    return mapOf(
        "id" to id,
        "name" to name,
        "sprite" to sprites.front_default,
        "type" to types.firstOrNull()?.type?.name,
        "height" to height
    )
}
