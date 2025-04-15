package com.example.cleanarchitecture.ui.components.bottomNavBar

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cleanarchitecture.R

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
