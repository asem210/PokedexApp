package com.example.cleanarchitecture.ui.features.login

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.ui.components.header.TopBar
import com.example.cleanarchitecture.ui.components.login.GoogleLoginButton
import com.example.cleanarchitecture.ui.components.login.LoginButtons
import com.example.cleanarchitecture.ui.theme.fontGrayLight
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseUser
import org.koin.androidx.compose.koinViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun LoginScreen(
    onLoginSuccess: (FirebaseUser?) -> Unit,
    viewModel: LoginViewModel = koinViewModel(),
    @SuppressLint("ContextCastToActivity") activity: ComponentActivity = LocalContext.current as ComponentActivity,
    context: Context = LocalContext.current
) {
    val loginResult by viewModel.loginResult.collectAsState()
    val error by viewModel.error.collectAsState()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        viewModel.handleGoogleSignInResult(result.data, activity)
    }

    // Mostrar mensaje de error
    error?.let {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    }

    // Manejar éxito
    LaunchedEffect(loginResult) {
        loginResult?.let { user ->
            onLoginSuccess(user)
        }
    }

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("696268675325-bakufgntubo71i1l8cits8o3dpanh1b8.apps.googleusercontent.com") // Reemplazar por el correcto
        .requestEmail()
        .build()
    val googleSignInClient = GoogleSignIn.getClient(context, gso)


        if (loginResult != null) {
            Column {
                // Usuario logueado
                Text(
                    text = "Bienvenido, ${loginResult?.displayName ?: "Usuario"}",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { viewModel.logout() }) {
                    Text("Cerrar sesión")
                }
            }

        } else {
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
                        onBackClick = { /* Acción para retroceder */ },
                        onFavoriteClick = { /* Acción para el favorito */ },
                        showFavoriteButton = false,
                        title = "LogIn"
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Image(
                        painter = painterResource(id = R.drawable.login_icon),
                        contentDescription = "Login Icon",
                        modifier = Modifier.size(400.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Hey, nice to see you again!",
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = Color.Black,
                        fontSize = 26.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "How would you like to connect?",
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        color = fontGrayLight,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }

                // Este bloque queda abajo siempre
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(bottom = 64.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LoginButtons(
                        onGoogleLoginClick = {
                            val signInIntent = googleSignInClient.signInIntent
                            launcher.launch(signInIntent)
                        },
                        onEmailLoginClick = {}
                    )
                }
            }

        }


}
