package com.example.domain.usecase.pokemon

import android.util.Log
import com.example.domain.model.Pokemon
import com.example.domain.model.PokemonSpecies
import com.example.domain.repository.PokemonLocalRepository

class LocalPokemonUseCase(private val repository: PokemonLocalRepository) {

    suspend fun savePokemon(pokemon: Pokemon) {
        val existingPokemon = repository.getPokemonByName(pokemon.name)

        if (existingPokemon == null) {
            repository.insertPokemon(pokemon)
            Log.d("PokemonUseCase", "Pokémon guardado exitosamente: ${pokemon.name}")
        } else {
            Log.d("PokemonUseCase", "El Pokémon ${pokemon.name} ya está guardado: $existingPokemon")
        }
    }

    suspend fun getSavedPokemon(name: String): Pokemon? {
        return repository.getPokemonByName(name)
    }

    suspend fun savePokemonSpecies(pokemonSpecies: PokemonSpecies){
        val existingPokemonSpecies = repository.getPokemonSpeciesByName(pokemonSpecies.name)

        if (existingPokemonSpecies==null){
            repository.insertPokemonSpecies(pokemonSpecies)
            Log.d("PokemonUseCase", "Especie pokémon guardada exitosamente: ${pokemonSpecies.name}")
        } else{
            Log.d("PokemonUseCase", "El Pokémon ${pokemonSpecies.name} ya está guardado: $existingPokemonSpecies")
        }
    }

    suspend fun getSavedPokemonSpecie(name:String):PokemonSpecies?{
        return repository.getPokemonSpeciesByName(name)
    }
}