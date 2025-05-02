package com.example.cleanarchitecture.ui.features.favorites

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.model.Pokemon
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class FavoriteViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()  // Instancia de Firestore

    // Cambia el LiveData a mutableStateOf
    var isFavorite by mutableStateOf<Boolean?>(null) // Usamos Boolean? para poder saber si estamos esperando
        private set

    private val _favoritePokemons = MutableLiveData<List<Pokemon>>()
    val favoritePokemons: LiveData<List<Pokemon>> = _favoritePokemons

    // Verificar si el Pokémon está en favoritos
    fun checkIfFavorite(userId: String, pokemonId: Int) {
        viewModelScope.launch {
            try {
                // Acceder a Firestore para verificar si el Pokémon es favorito
                val docRef = firestore.collection("favorites")
                    .document(userId)
                    .collection("pokemon")
                    .document(pokemonId.toString())

                docRef.get().addOnSuccessListener { document ->
                    val isFav = document.exists() // Si el documento existe, es favorito
                    isFavorite = isFav
                    Log.d("FavoriteViewModel", "checkIfFavorite éxito: Pokémon ID $pokemonId es favorito: $isFav")
                }.addOnFailureListener { exception ->
                    Log.e("FavoriteViewModel", "Error checking favorite: ${exception.message}", exception)
                    isFavorite = false // En caso de error, podemos asumir que no es favorito
                }
            } catch (e: Exception) {
                Log.e("FavoriteViewModel", "Error checking favorite: ${e.message}", e)
                isFavorite = false // En caso de error, podemos asumir que no es favorito
            }
        }
    }

    // Agregar un Pokémon a los favoritos
    fun addFavorite(userId: String, pokemon: Pokemon) {
        viewModelScope.launch {
            try {
                // Guardar el Pokémon en Firestore
                val docRef = firestore.collection("favorites")
                    .document(userId)
                    .collection("pokemon")
                    .document(pokemon.id.toString())

                docRef.set(pokemon).addOnSuccessListener {
                    isFavorite = true
                    Log.d("FavoriteViewModel", "addFavorite éxito: Pokémon ID ${pokemon.id} agregado a favoritos.")
                }.addOnFailureListener { exception ->
                    Log.e("FavoriteViewModel", "Error adding favorite: ${exception.message}", exception)
                }
            } catch (e: Exception) {
                Log.e("FavoriteViewModel", "Error adding favorite: ${e.message}", e)
            }
        }
    }

    // Eliminar un Pokémon de los favoritos
    fun removeFavorite(userId: String, pokemon: Pokemon) {
        viewModelScope.launch {
            try {
                // Eliminar el Pokémon de Firestore
                val docRef = firestore.collection("favorites")
                    .document(userId)
                    .collection("pokemon")
                    .document(pokemon.id.toString())

                docRef.delete().addOnSuccessListener {
                    isFavorite = false
                    Log.d("FavoriteViewModel", "removeFavorite éxito: Pokémon ID ${pokemon.id} eliminado de favoritos.")
                }.addOnFailureListener { exception ->
                    Log.e("FavoriteViewModel", "Error removing favorite: ${exception.message}", exception)
                }
            } catch (e: Exception) {
                Log.e("FavoriteViewModel", "Error removing favorite: ${e.message}", e)
            }
        }
    }

    // Cargar los favoritos
    fun loadFavorites(userId: String) {
        viewModelScope.launch {
            try {
                val collectionRef = firestore.collection("favorites")
                    .document(userId)
                    .collection("pokemon")

                collectionRef.get().addOnSuccessListener { result ->
                    val favoriteList = result.mapNotNull { document ->
                        document.toObject(Pokemon::class.java)
                    }
                    _favoritePokemons.postValue(favoriteList)
                    Log.d("FavoriteViewModel", "loadFavorites éxito: Se cargaron ${favoriteList.size} favoritos para el usuario $userId.")
                }.addOnFailureListener { exception ->
                    Log.e("FavoriteViewModel", "Error loading favorites: ${exception.message}", exception)
                }
            } catch (e: Exception) {
                Log.e("FavoriteViewModel", "Error loading favorites: ${e.message}", e)
            }
        }
    }
}
