package com.example.domain.usecase.pokemon

import com.example.domain.model.PokemonSpecies
import com.example.domain.repository.PokemonRepository

class GetPokemonSpeciesByNameUseCase(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(name: String): PokemonSpecies {
        return repository.getPokemonSpeciesByName(name)
    }
}