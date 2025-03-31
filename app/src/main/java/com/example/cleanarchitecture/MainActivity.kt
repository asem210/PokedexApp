package com.example.cleanarchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cleanarchitecture.ui.features.home.HomeComponent
import com.example.cleanarchitecture.ui.features.home.HomeViewModel
import com.example.cleanarchitecture.ui.theme.CleanArchitectureTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CleanArchitectureTheme {
                val navController = rememberNavController()
                Scaffold (
                    modifier=Modifier.fillMaxSize(),
                    bottomBar = {BottomNavigationBar(navController)}
                    ){
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        NavHost(navController=navController, startDestination = "home"){
                            composable ("home") {
                                HomeComponent(navController)
                            }
                            composable ("favorite") {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Text(text = "Favorite")
                                }
                            }
                            composable ("region") {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Text(text = "Region")
                                }
                            }
                            composable ("pokedex") {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Text(text = "Pokedex")
                                }
                            }
                            composable ("profile") {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Text(text = "Profile")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        //current route
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        val items = listOf(
            BottomNavItems.Pokedex,
            BottomNavItems.Favorite,
            BottomNavItems.Profile,
            BottomNavItems.Region
        )

        items.forEach { item ->
            val isSelected = currentRoute == item.route
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    item.route?.let {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { startRoute ->
                                popUpTo(startRoute) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                label = { Text(text = item.tittle) },
                icon = {
                    Image(
                        painter = painterResource(id = if (isSelected) item.selectedIcon else item.icon),
                        contentDescription = null
                    )
                },
                colors = NavigationBarItemDefaults.colors().copy(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = Color.Gray,
                    unselectedIconColor = Color.Gray
                )
            )
        }
    }
}

sealed class BottomNavItems(val route:String?, val tittle:String, val icon:Int, val selectedIcon:Int){
    object Pokedex : BottomNavItems("home", "Pokedex", icon = R.drawable.ic_pokemon_default, selectedIcon = R.drawable.ic_pokemon_selected)
    object Profile: BottomNavItems ("profile", "Profile", icon= R.drawable.ic_profile_default, selectedIcon = R.drawable.ic_profile_selected)
    object Region: BottomNavItems("region", "Regions", icon = R.drawable.ic_region_default, selectedIcon = R.drawable.ic_region_selected)
    object Favorite: BottomNavItems("favorite","Favorites", icon = R.drawable.ic_favorite_default, selectedIcon = R.drawable.ic_favorite_selected)
}

