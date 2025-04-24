package com.example.cleanarchitecture.ui.components.form.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class RegisterFormViewModel : ViewModel() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _email = MutableLiveData("")
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> get() = _password

    private val _isEmailValid = MutableLiveData(false)
    val isEmailValid: LiveData<Boolean> get() = _isEmailValid

    private val _isPasswordValid = MutableLiveData(false)
    val isPasswordValid: LiveData<Boolean> get() = _isPasswordValid

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
        _isEmailValid.value = Regex("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$").matches(newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
        _isPasswordValid.value = Regex("^.{6,}$").matches(newPassword)
    }

    fun registerUser(onResult: (Boolean, String) -> Unit) {
        val userEmail = email.value.orEmpty()
        val userPassword = password.value.orEmpty()

        viewModelScope.launch {
            firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        firebaseAuth.currentUser?.sendEmailVerification()
                        onResult(true, "Account created! A verification email has been sent.")
                    } else {
                        onResult(false, task.exception?.message ?: "Registration failed")
                    }
                }
        }
    }
}
