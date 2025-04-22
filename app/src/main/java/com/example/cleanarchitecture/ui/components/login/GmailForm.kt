package com.example.cleanarchitecture.ui.components.login

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import com.example.cleanarchitecture.ui.components.input.CustomInputField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cleanarchitecture.ui.features.login.viewmodel.LoginFormViewModel

@Composable
fun LoginForm(
    viewModel: LoginFormViewModel = viewModel(),
    navController: NavController
) {
    val email by viewModel.email.observeAsState("")
    val password by viewModel.password.observeAsState("")

    val isEmailValid by viewModel.isEmailValid.observeAsState(false)
    val isPasswordValid by viewModel.isPasswordValid.observeAsState(false)
    val isFormValid = isEmailValid && isPasswordValid

    val context = LocalContext.current


    Column(modifier = Modifier.padding(16.dp)) {
        CustomInputField(
            value = email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = "Gmail",
            validationRegex = Regex("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"),
            errorMessage = "Please enter a valid Gmail address"
        )
        CustomInputField(
            value = password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = "Password",
            isPassword = true,
            validationRegex = Regex("^.{6,}$"),
            errorMessage = "Password must be at least 6 characters long."
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón de autenticación
        Button(
            onClick = {
                viewModel.authenticateUser(
                    onSuccess = {
                        // Acción en caso de éxito
                        Toast.makeText(context, "Logged in successfully", Toast.LENGTH_SHORT).show()
                        navController.navigate("profile")
                    },
                    onFailure = { errorMessage ->
                        // Acción en caso de error
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                )
            },
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Log In")
        }
    }
}

