package com.example.cleanarchitecture.ui.features.loginScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginViewModel : ViewModel(), KoinComponent {

    // Inyecta FirebaseAuth usando Koin
    private val firebaseAuth: FirebaseAuth by inject()

    // Función para manejar el inicio de sesión con credenciales de Google
    fun signInWithCredential(credential: AuthCredential, onResult: (Boolean) -> Unit) {
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true)
                } else {
                    onResult(false)
                }
            }
    }

    // Obtener el usuario actual
    fun getCurrentUser() = firebaseAuth.currentUser
}
