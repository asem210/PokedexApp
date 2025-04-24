package com.example.domain.usecase.pokemon

import android.util.Log
import com.example.domain.model.Pokemon
import com.example.domain.repository.PokemonLocalRepository

class SavePokemonUseCase(private val repository: PokemonLocalRepository) {

    suspend operator fun invoke(pokemon: Pokemon) {
        // Verifica si el Pokémon ya existe en la base de datos
        val existingPokemon = repository.getPokemonByName(pokemon.name)

        if (existingPokemon == null) {
            // Si el Pokémon no existe, lo guardamos
            repository.insertPokemon(pokemon)
            Log.d("SavePokemonUseCase", "Pokémon guardado exitosamente: ${pokemon.name}")
        } else {
            // Si el Pokémon ya existe, lo imprimimos
            Log.d("SavePokemonUseCase", "El Pokémon ${pokemon.name} ya está guardado: $existingPokemon")
        }
    }
}
