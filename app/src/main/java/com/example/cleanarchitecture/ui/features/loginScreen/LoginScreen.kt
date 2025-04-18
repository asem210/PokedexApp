package com.example.cleanarchitecture.ui.features.loginScreen

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun LoginScreen(
    onLoginSuccess: (FirebaseUser?) -> Unit,
    context: Context = LocalContext.current,
    @SuppressLint("ContextCastToActivity") activity: ComponentActivity = LocalContext.current as ComponentActivity
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        Log.d("LoginScreen", "Resultado de la actividad: $result") // Log para verificar que tenemos el resultado

        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            Log.d("LoginScreen", "Intentando obtener la cuenta de Google...") // Log antes de obtener la cuenta
            val account = task.getResult(ApiException::class.java)
            Log.d("LoginScreen", "Cuenta obtenida: ${account?.email}") // Log para verificar que la cuenta se obtuvo

            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            Log.d("LoginScreen", "Credencial obtenida, intentando iniciar sesión en Firebase...") // Log para credencial

            FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(activity) { authResult ->
                    if (authResult.isSuccessful) {
                        val user = authResult.result?.user
                        Log.d("LoginScreen", "Inicio de sesión exitoso, usuario: ${user?.email}") // Log para verificar si el usuario se obtuvo
                        onLoginSuccess(user)
                    } else {
                        Log.e("LoginScreen", "Error al iniciar sesión en Firebase: ${authResult.exception?.message}") // Log para errores al iniciar sesión
                        Toast.makeText(context, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: ApiException) {
            Log.e("LoginScreen", "Error al obtener cuenta de Google: ${e.message}") // Log para capturar errores en el proceso de obtener la cuenta
            Toast.makeText(context, "Error al obtener cuenta: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("696268675325-bakufgntubo71i1l8cits8o3dpanh1b8.apps.googleusercontent.com") // 👈 Usa el que aparece en Firebase > Auth > Método de acceso > Google
        .requestEmail()
        .build()
    val googleSignInClient = GoogleSignIn.getClient(context, gso)

    // UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Iniciar Sesión", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                Log.d("LoginScreen", "Botón de Google Sign In presionado") // Log al presionar el botón
                val signInIntent = googleSignInClient.signInIntent
                launcher.launch(signInIntent)
            }
        ) {
            Text("Iniciar sesión con Google")
        }
    }
}

