package com.example.domain.usecase.pokemon

import com.example.domain.model.Pokemon
import com.example.domain.repository.PokemonRepository

class GetPokemonByNameUseCase(private val repository: PokemonRepository) {
    suspend operator fun invoke(name: String): Pokemon {
        return repository.getPokemonByName(name)
    }
}