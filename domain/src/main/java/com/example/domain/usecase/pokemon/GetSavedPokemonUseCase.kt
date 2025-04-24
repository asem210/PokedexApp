package com.example.domain.usecase.pokemon

import com.example.domain.model.Pokemon
import com.example.domain.repository.PokemonLocalRepository

class GetSavedPokemonUseCase(private val repository: PokemonLocalRepository) {
    operator fun invoke(name: String): Pokemon? {
        return repository.getPokemonByName(name)
    }
}