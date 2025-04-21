package com.example.cleanarchitecture.ui.features.login

import android.content.Intent
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel(
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _loginResult = MutableStateFlow<FirebaseUser?>(null)
    val loginResult: StateFlow<FirebaseUser?> = _loginResult

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        _loginResult.value = auth.currentUser
    }

    fun handleGoogleSignInResult(data: Intent?, activity: ComponentActivity) {

        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)

            val credential = GoogleAuthProvider.getCredential(account.idToken, null)

            auth.signInWithCredential(credential)
                .addOnCompleteListener(activity) { authResult ->
                    if (authResult.isSuccessful) {
                        val user = authResult.result?.user
                        _loginResult.value = user
                    } else {
                        val errorMessage = authResult.exception?.message ?: "Error desconocido"
                        _error.value = errorMessage
                    }
                }
        } catch (e: ApiException) {
            val errorMsg = e.message ?: "Error desconocido al obtener cuenta"
            _error.value = errorMsg
        }
    }

    fun logout() {
        auth.signOut()
        _loginResult.value = null
        _error.value = null
    }
}
