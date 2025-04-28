package com.example.cleanarchitecture

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cleanarchitecture.ui.features.home.HomeComponent
import com.example.cleanarchitecture.ui.theme.CleanArchitectureTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import org.koin.androidx.compose.koinViewModel
import android.Manifest
import android.content.pm.ActivityInfo
import com.example.cleanarchitecture.ui.components.bottomNavBar.BottomNavigationBar
import com.example.cleanarchitecture.ui.components.floattingButton.FloatingButton
import com.example.cleanarchitecture.ui.components.form.view.RegisterForm
import com.example.cleanarchitecture.ui.components.scanner.ScanScreen
import com.example.cleanarchitecture.ui.features.login.view.GmailLoginScreen
import com.example.cleanarchitecture.ui.features.login.view.LoginScreen
import com.example.cleanarchitecture.ui.features.login.view.PasswordRecoveryScreen
import com.example.cleanarchitecture.ui.features.login.view.RegisterScreen
import com.example.cleanarchitecture.ui.features.pokemon.PokemonDetailScreen
import com.google.accompanist.permissions.PermissionStatus
import com.google.firebase.FirebaseApp


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
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
                        if (currentRoute != "pokemon_detail/{pokemonCode}" &&
                            currentRoute != "scan" &&
                            currentRoute?.startsWith("profile") != true
                        ) {
                            BottomNavigationBar(navController)
                        }
                    },
                    floatingActionButton = {
                        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
                        if (
                            currentRoute != "scan" &&
                            currentRoute?.startsWith("profile") != true
                        ) {
                            FloatingButton {
                                when (permissionState.status) {
                                    is PermissionStatus.Granted -> {
                                        navController.navigate("scan")
                                    }
                                    is PermissionStatus.Denied -> {
                                        permissionState.launchPermissionRequest()
                                    }
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
                                LoginScreen(
                                    onLoginSuccess = { user ->
                                        Log.d("Login", "Usuario autenticado: ${user?.displayName}")
                                    },
                                    navController = navController
                                )
                            }
                            composable("profile/login/gmail") {
                                GmailLoginScreen(navController = navController)
                            }

                            composable("profile/login/gmail/recovery"){
                                PasswordRecoveryScreen(navController=navController)
                            }
                            composable("profile/login/gmail/register"){
                                RegisterScreen(navController=navController)
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





