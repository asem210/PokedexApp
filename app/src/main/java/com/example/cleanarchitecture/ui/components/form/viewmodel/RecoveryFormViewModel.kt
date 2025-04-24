package com.example.cleanarchitecture.ui.components.form.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class RecoveryFormViewModel() : ViewModel() {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _email = MutableLiveData("")
    val email: LiveData<String> get() = _email

    private val _isEmailValid = MutableLiveData(false)
    val isEmailValid: LiveData<Boolean> = _isEmailValid

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
        _isEmailValid.value = Regex("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$").matches(newEmail)

    }

    fun sendRecoveryEmail(
        onResult: (success: Boolean, message: String) -> Unit
    ) {
        val currentEmail = _email.value ?: ""
        if (_isEmailValid.value == true) {
            firebaseAuth
                .sendPasswordResetEmail(currentEmail)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onResult(true, "Recovery email sent successfully. Check your inbox.")
                    } else {
                        val error = task.exception?.message ?: "An error occurred. Please try again."
                        onResult(false, error)
                    }
                }
        } else {
            onResult(false, "Invalid email address")
        }
    }
}