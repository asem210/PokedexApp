package com.example.data.local.db

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase

class PokemonDbHelper(context: Context) : SQLiteOpenHelper(context, "pokemon_db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
        CREATE TABLE pokemon (
            id INTEGER PRIMARY KEY,
            name TEXT,
            base_experience INTEGER,
            height INTEGER,
            weight INTEGER,
            ordering INTEGER,
            abilities_json TEXT,
            types_json TEXT,
            sprites_json TEXT,
            stats_json TEXT
        )
    """.trimIndent())

        db.execSQL("""
        CREATE TABLE pokemon_species (
            name TEXT PRIMARY KEY,
            flavor_text_entries_json TEXT,
            gender_rate INTEGER,
            genera_json TEXT
        )
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS pokemon")
        onCreate(db)
    }
}