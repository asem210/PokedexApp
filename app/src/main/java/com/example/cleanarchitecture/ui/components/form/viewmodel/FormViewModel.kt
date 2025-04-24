package com.example.cleanarchitecture.ui.components.form.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class LoginFormViewModel() : ViewModel() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()


    private val _email = MutableLiveData("")
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> get() = _password

    private val _isEmailValid = MutableLiveData(false)
    val isEmailValid: LiveData<Boolean> = _isEmailValid

    private val _isPasswordValid = MutableLiveData(false)
    val isPasswordValid: LiveData<Boolean> = _isPasswordValid

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
        _isEmailValid.value = Regex("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$").matches(newEmail)

    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
        _isPasswordValid.value = Regex("^.{6,}$").matches(newPassword)

    }

    fun authenticateUser(onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val userEmail: String = email.value.toString()
        val userPassword: String = password.value.toString()

        viewModelScope.launch {
            try {
                firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            onSuccess()  // Acceso exitoso
                        } else {
                            onFailure("Authentication failed. Please try again.")  // Error de autenticación
                        }
                    }
            } catch (e: Exception) {
                onFailure("Error: ${e.message}")
            }
        }
    }
}