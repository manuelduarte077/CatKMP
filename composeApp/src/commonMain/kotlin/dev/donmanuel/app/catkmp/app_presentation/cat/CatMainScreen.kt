package dev.donmanuel.app.catkmp.app_presentation.cat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.donmanuel.app.catkmp.app_presentation.core.navigation.BottomBarItem.BottomItemCatFavorites
import dev.donmanuel.app.catkmp.app_presentation.core.navigation.BottomBarItem.BottomItemCatList
import dev.donmanuel.app.catkmp.app_presentation.core.navigation.BottomNavigation
import dev.donmanuel.app.catkmp.app_presentation.core.navigation.NavigationBottomWrapper
import dev.donmanuel.app.catkmp.app_presentation.core.navigation.SCREEN_CAT_LIST

@Composable
fun MainScreen(mainNavController: NavController) {

    val navController = rememberNavController()
    val items = listOf(BottomItemCatList(), BottomItemCatFavorites())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Column {
                BottomNavigation(navController, items)
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            NavigationBottomWrapper(mainNavController, navController, SCREEN_CAT_LIST)
        }
    }
}
