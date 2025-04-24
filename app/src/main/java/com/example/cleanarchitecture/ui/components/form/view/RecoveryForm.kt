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
import com.example.cleanarchitecture.ui.components.form.viewmodel.LoginFormViewModel
import com.example.cleanarchitecture.ui.components.form.viewmodel.RecoveryFormViewModel
import com.example.cleanarchitecture.ui.components.input.CustomInputField
import com.example.cleanarchitecture.ui.theme.fontGrayMedium

@Composable
fun RecoveryForm(
    viewModel: RecoveryFormViewModel = viewModel(),
    navController: NavController
) {
    val email by viewModel.email.observeAsState("")
    val isEmailValid by viewModel.isEmailValid.observeAsState(false)
    val isFormValid = isEmailValid
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
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.sendRecoveryEmail { success, message ->
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    if (success) {
                        navController.navigate("home")
                    }
                }
            },
            enabled = isEmailValid,
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
        ) {
            Text(
                text = "Recover Password",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 18.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "If your email is registered, you'll receive a recovery link shortly.",
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            fontSize = 14.sp,
            modifier = Modifier.fillMaxWidth(),
            color = fontGrayMedium
        )
    }

}