package com.example.cleanarchitecture.ui.components.scanner

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun ScanScreen(
    navController: NavController,
    onCodeScanned: (String) -> Unit
) {



    var qrCodeDetectedMessage by remember { mutableStateOf<String?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Usa tu componente personalizado
        ScanCode(
            modifier = Modifier.fillMaxSize(),
            onQrCodeDetected = { code ->
                qrCodeDetectedMessage = code // Almacena el código detectado
                onCodeScanned(code)
                // Llamamos al ViewModel para obtener el Pokémon
//                pokemonViewModel.fetchPokemon(code)
                // Mostrar el código en el log
                Log.d("QRCodeDetected", "Código QR Detectado: $code")
                // Navegamos a la pantalla de detalles del Pokémon, pasando el código como argumento
                navController.navigate("pokemon_detail/$code")
            }
        )

        // Mensaje opcional mientras se escanea
        Text(
            text = qrCodeDetectedMessage ?: "Escaneando...",
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}