package com.example.myrecipeapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myrecipeapp.R
import com.example.myrecipeapp.ui.theme.Brown
import com.example.myrecipeapp.ui.theme.LightBrown

data class TopLevelRoute<T : Any>(
    val name: String,
    val route: T,
    val icon: ImageVector,
    val selectedIcon: ImageVector
)

@Composable
fun NavigationBottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val topLevelRoutes = listOf(
        TopLevelRoute(
            stringResource(R.string.meals),
            NavRoutes.CategoryScreen,
            Icons.Outlined.Home,
            Icons.Filled.Home
        ),
        TopLevelRoute(
            stringResource(R.string.favorites),
            NavRoutes.FavoriteScreen,
            Icons.Outlined.FavoriteBorder,
            Icons.Filled.Favorite
        )
    )

    val navItemColors = NavigationBarItemDefaults.colors(
        selectedIconColor = Color.White,
        unselectedIconColor = Color.White,
        unselectedTextColor = Color.White,
        selectedTextColor = Color.White,
        indicatorColor = LightBrown,
    )

    NavigationBar(containerColor = Brown) {
        topLevelRoutes.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any {
                it.hasRoute(screen.route::class)
            } == true
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (isSelected) screen.selectedIcon else screen.icon,
                        contentDescription = screen.name,
                    )
                },
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                colors = navItemColors,
                label = { Text(screen.name) }
            )
        }

    }
}

