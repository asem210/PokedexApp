package com.example.data.network.dto

import com.example.domain.model.Pokedex
import com.example.domain.model.PokedexEntry

data class PokedexDto(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokedexEntryDto>
){
    fun toDomain()= Pokedex(
        count=count,
        next=next,
        previous=previous,
        results=results.map {it.toDomain()}
    )
}

data class PokedexEntryDto(
    val name: String,
    val url: String
){
    fun toDomain()=PokedexEntry(
        name=name,
        url=url
    )
}
