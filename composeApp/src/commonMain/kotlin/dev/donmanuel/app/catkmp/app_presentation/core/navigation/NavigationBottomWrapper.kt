package dev.donmanuel.app.catkmp.app_presentation.core.navigation

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.donmanuel.app.catkmp.app_presentation.cat.CatFavoriteScreen
import dev.donmanuel.app.catkmp.app_presentation.cat.CatListScreen
import org.jetbrains.compose.resources.stringResource

const val SCREEN_CAT_LIST = "SCREEN_CAT_LIST"
const val SCREEN_CAT_FAVORITES = "SCREEN_CAT_FAVORITES"

@Composable
fun NavigationBottomWrapper(
    mainNavController: NavController,
    bottomNavController: NavHostController,
    startDestination: String
) {

    NavHost(navController = bottomNavController, startDestination = startDestination) {
        composable(route = SCREEN_CAT_LIST) { CatListScreen(mainNavController) }
        composable(route = SCREEN_CAT_FAVORITES) { CatFavoriteScreen(mainNavController) }
    }
}

@Composable
fun BottomNavigation(navController: NavHostController, items: List<BottomBarItem>) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        tonalElevation = 0.dp
    ) {
        items.forEach { item ->
            val selected = currentDestination?.route == item.route

            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.LightGray.copy(alpha = 0.5f)
            ),
                icon = item.icon,
                label = {
                    Text(
                        text = stringResource(item.titleResId),
                        fontSize = 14.sp,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                    )
                },
                onClick = {
                    navController.navigate(route = item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
            )
        }
    }
}