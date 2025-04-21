package com.example.cleanarchitecture.ui.components.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.ui.theme.emailButton

@Composable
fun GoogleLoginButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "Login with Google",
    iconId: Int = R.drawable.google_login
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(48.dp),
        shape = RoundedCornerShape(100.dp),
        border = BorderStroke(1.dp, Color.Gray),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(12.dp))

            Image(
                painter = painterResource(id = iconId),
                contentDescription = "Google Icon",
                modifier = Modifier.size(27.16.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = text,
                fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun EmailLoginButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "Continue with Email"
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(48.dp),
        shape = RoundedCornerShape(100.dp),
        border = BorderStroke(1.dp, emailButton),
        colors = ButtonDefaults.buttonColors(
            containerColor = emailButton, // Azul
            contentColor = Color.White // Texto blanco
        )

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = text,
                fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun LoginButtons(
    onGoogleLoginClick: () -> Unit,
    onEmailLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
    ) {
        GoogleLoginButton(
            onClick = onGoogleLoginClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        EmailLoginButton(
            onClick = onEmailLoginClick
        )
    }
}