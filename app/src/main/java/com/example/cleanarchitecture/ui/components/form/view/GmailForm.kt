package com.example.cleanarchitecture.ui.components.form.view

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.ui.components.form.viewmodel.LoginFormViewModel

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
            errorMessage = "Please enter a valid Gmail address",
            placeholder = "pazosa213@gmail.com"
        )
        Spacer(modifier = Modifier.height(4.dp))
        CustomInputField(
            value = password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = "Password",
            isPassword = true,
            validationRegex = Regex("^.{6,}$"),
            errorMessage = "Password must be at least 6 characters long.",
            placeholder = "test123"
        )

        Spacer(modifier = Modifier.height(4.dp))

        // "¿Olvidaste tu contraseña?" y "Crear cuenta"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Forgot password?",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                color = Color(0xFF1E88E5), // azul
                modifier = Modifier.clickable {
                    navController.navigate("profile/login/gmail/recovery")
                }
            )

            Text(
                text = "Create account",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                color = Color(0xFF1E88E5), // azul
                modifier = Modifier.clickable {
                    navController.navigate("profile/login/gmail/register")
                }
            )
        }


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
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)

        ) {
            Text(
                text="Log In",
                style = TextStyle(
                    fontFamily = FontFamily(Font( R.font.poppins_medium)),
                    fontSize = 18.sp
                )
            )
        }
    }
}

