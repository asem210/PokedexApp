package com.example.domain.usecase.pokedex

import com.example.domain.model.Pokedex
import com.example.domain.repository.PokedexRepository

class GetPokedexUseCase(private val repository: PokedexRepository) {
    suspend operator fun invoke(): Pokedex{
        return repository.getPokedex()
    }
}