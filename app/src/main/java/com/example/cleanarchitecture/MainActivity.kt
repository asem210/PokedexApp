package com.example.cleanarchitecture

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cleanarchitecture.ui.features.home.HomeComponent
import com.example.cleanarchitecture.ui.features.home.HomeViewModel
import com.example.cleanarchitecture.ui.theme.CleanArchitectureTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import org.koin.androidx.compose.koinViewModel
import android.Manifest
import android.content.pm.ActivityInfo
import androidx.camera.view.PreviewView
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.cleanarchitecture.ui.components.bottomNavBar.BottomNavigationBar
import com.example.cleanarchitecture.ui.components.floattingButton.FloatingButton
import com.example.cleanarchitecture.ui.components.scanner.ScanCode
import com.example.cleanarchitecture.ui.components.scanner.ScanScreen
import com.example.cleanarchitecture.ui.features.pokemon.PokemonDetailScreen
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.shouldShowRationale


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        enableEdgeToEdge()

        setContent {
            CleanArchitectureTheme {
                val navController = rememberNavController()
                val permissionState = rememberPermissionState(Manifest.permission.CAMERA)

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
                        if (currentRoute != "pokemon_detail/{pokemonCode}" && currentRoute!= "scan") {
                            BottomNavigationBar(navController)
                        }
                    },
                    floatingActionButton = {
                        FloatingButton {
                            when (permissionState.status) {
                                is PermissionStatus.Granted -> {
                                    // Permiso ya otorgado, navega al escáner
                                    navController.navigate("scan")
                                }
                                is PermissionStatus.Denied -> {
                                    // Solicitar permiso
                                    permissionState.launchPermissionRequest()
                                }
                            }
                        }
                    }
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        NavHost(navController = navController, startDestination = "home") {
                            composable("home") { HomeComponent(navController) }
                            composable("favorite") {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Text(text = "Favorite")
                                }
                            }
                            composable("region") {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Text(text = "Region")
                                }
                            }
                            composable("pokedex") {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Text(text = "Pokedex")
                                }
                            }
                            composable("profile") {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Text(text = "Profile")
                                }
                            }
                            composable("scan") {
                                ScanScreen(
                                    navController,
                                    onCodeScanned = { code ->
                                        Log.d("Scan", "Código escaneado: $code")
                                        navController.popBackStack() // Vuelve al home o anterior
                                    }
                                )
                            }
                            composable("pokemon_detail/{pokemonCode}") { backStackEntry ->
                                val pokemonCode = backStackEntry.arguments?.getString("pokemonCode") ?: ""
                                PokemonDetailScreen(
                                    navController = navController,
                                    pokemonCode = pokemonCode,
                                    pokemonViewModel = koinViewModel()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}





