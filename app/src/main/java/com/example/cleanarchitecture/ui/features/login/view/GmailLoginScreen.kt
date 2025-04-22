package com.example.cleanarchitecture.ui.features.login.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.ui.components.header.TopBar
import com.example.cleanarchitecture.ui.components.login.LoginForm
import com.example.cleanarchitecture.ui.features.login.viewmodel.LoginViewModel
import com.example.cleanarchitecture.ui.theme.fontGrayMedium
import org.koin.androidx.compose.koinViewModel

@Composable
fun GmailLoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    navController: NavController
    )
{
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(
                onFavoriteClick = { /* Acción para el favorito */ },
                showFavoriteButton = false,
                title = "LogIn"
            )
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                "Welcome back!",
                fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                color = fontGrayMedium,
                fontSize = 26.sp,
                textAlign = TextAlign.Center
            )
            Text(
                "Complete your information",
                fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                color = Color.Black,
                fontSize = 26.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            LoginForm(navController = navController)
        }
    }

}