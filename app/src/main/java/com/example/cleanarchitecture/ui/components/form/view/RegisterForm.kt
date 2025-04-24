package com.example.cleanarchitecture.ui.components.form.view

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.ui.components.form.viewmodel.RegisterFormViewModel
import com.example.cleanarchitecture.ui.components.input.CustomInputField

@Composable
fun RegisterForm(
    viewModel: RegisterFormViewModel = viewModel(),
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
            label = "Email",
            validationRegex = Regex("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"),
            errorMessage = "Enter a valid email address",
            placeholder = "example@gmail.com"
        )
        Spacer(modifier = Modifier.height(4.dp))
        CustomInputField(
            value = password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = "Password",
            isPassword = true,
            validationRegex = Regex("^.{6,}$"),
            errorMessage = "Password must be at least 6 characters",
            placeholder = "mypassword123"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.registerUser { success, message ->
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    if (success) {
                        navController.navigate("profile")
                    }
                }
            },
            enabled = isFormValid,
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
        ) {
            Text(
                text = "Create Account",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 18.sp
                )
            )
        }

    }
}
