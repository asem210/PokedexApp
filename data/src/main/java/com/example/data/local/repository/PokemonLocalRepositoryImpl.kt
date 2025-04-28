package com.example.data.local.repository
import android.content.ContentValues
import android.content.Context
import com.example.domain.model.*
import com.example.domain.repository.PokemonRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.data.local.db.PokemonDbHelper
import com.example.domain.repository.PokemonLocalRepository

class PokemonLocalRepositoryImpl(
    context: Context
) : PokemonLocalRepository {

    private val dbHelper = PokemonDbHelper(context)
    private val gson = Gson()

    override suspend fun insertPokemon(pokemon: Pokemon) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("id", pokemon.id)
            put("name", pokemon.name)
            put("base_experience", pokemon.base_experience)
            put("height", pokemon.height)
            put("weight", pokemon.weight)
            put("ordering", pokemon.order)
            put("abilities_json", gson.toJson(pokemon.abilities))
            put("types_json", gson.toJson(pokemon.types))
            put("sprites_json", gson.toJson(pokemon.sprites))
            put("stats_json", gson.toJson(pokemon.stats))
        }
        db.insert("pokemon", null, values)
        db.close()
    }

    override suspend fun getPokemonByName(name: String): Pokemon? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            "pokemon",
            null,
            "name = ?",
            arrayOf(name),
            null,
            null,
            null
        )

        val pokemon = if (cursor.moveToFirst()) {
            val abilities = gson.fromJson<List<AbilitySlot>>(cursor.getString(cursor.getColumnIndexOrThrow("abilities_json")), object : TypeToken<List<AbilitySlot>>() {}.type)
            val types = gson.fromJson<List<TypeSlot>>(cursor.getString(cursor.getColumnIndexOrThrow("types_json")), object : TypeToken<List<TypeSlot>>() {}.type)
            val sprites = gson.fromJson<Sprites>(cursor.getString(cursor.getColumnIndexOrThrow("sprites_json")), object : TypeToken<Sprites>() {}.type)
            val stats = gson.fromJson<List<Stats>>(cursor.getString(cursor.getColumnIndexOrThrow("stats_json")), object : TypeToken<List<Stats>>() {}.type)

            Pokemon(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                name = cursor.getString(cursor.getColumnIndexOrThrow("name")),
                base_experience = cursor.getInt(cursor.getColumnIndexOrThrow("base_experience")),
                height = cursor.getInt(cursor.getColumnIndexOrThrow("height")),
                weight = cursor.getInt(cursor.getColumnIndexOrThrow("weight")),
                order = cursor.getInt(cursor.getColumnIndexOrThrow("ordering")),
                abilities = abilities,
                types = types,
                sprites = sprites,
                stats = stats
            )
        } else null

        cursor.close()
        db.close()
        return pokemon
    }

    override suspend fun insertPokemonSpecies(pokemonSpecies: PokemonSpecies) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", pokemonSpecies.name)
            put("flavor_text_entries_json", gson.toJson(pokemonSpecies.flavorTextEntries))
            put("gender_rate", pokemonSpecies.gender_rate)
            put("genera_json", gson.toJson(pokemonSpecies.genera))
        }
        db.insert("pokemon_species", null, values)
        db.close()
    }

    // Obtener información de la especie de Pokémon por su nombre desde la tabla "pokemon_species"
    override suspend fun getPokemonSpeciesByName(name: String): PokemonSpecies? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            "pokemon_species",
            null,
            "name = ?",
            arrayOf(name),
            null,
            null,
            null
        )

        val pokemonSpecies = if (cursor.moveToFirst()) {
            val flavorTextEntries = gson.fromJson<List<FlavorTextEntry>>(cursor.getString(cursor.getColumnIndexOrThrow("flavor_text_entries_json")), object : TypeToken<List<FlavorTextEntry>>() {}.type)
            val genera = gson.fromJson<List<Genus>>(cursor.getString(cursor.getColumnIndexOrThrow("genera_json")), object : TypeToken<List<Genus>>() {}.type)

            PokemonSpecies(
                name = cursor.getString(cursor.getColumnIndexOrThrow("name")),
                flavorTextEntries = flavorTextEntries,
                gender_rate = cursor.getInt(cursor.getColumnIndexOrThrow("gender_rate")),
                genera = genera
            )
        } else null

        cursor.close()
        db.close()
        return pokemonSpecies
    }
}
