package dev.donmanuel.app.catkmp.app_presentation.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.donmanuel.app.catkmp.app_presentation.cat.CatDetailScreen
import dev.donmanuel.app.catkmp.app_presentation.cat.MainScreen
import dev.donmanuel.app.catkmp.app_presentation.login.LoginScreen
import dev.donmanuel.app.catkmp.app_presentation.login.SignupScreen
import dev.donmanuel.app.catkmp.data.local.repository.CatLocalRepository

const val SCREEN_LOGIN = "SCREEN_LOGIN"
const val SCREEN_SIGNUP = "SCREEN_SIGNUP"
const val SCREEN_CAT_MAIN = "SCREEN_CAT_MAIN"
const val SCREEN_CAT_DETAIL = "SCREEN_CAT_DETAIL"

/**
 * Sets up the navigation graph for the app, defining routes and associated composable screens.
 *
 * Initializes and remembers a navigation controller, and configures navigation between login, signup, main, and cat detail screens.
 */
@Composable
fun NavigationWrapper() {

    val mainNavController = rememberNavController()

    NavHost(navController = mainNavController, startDestination = SCREEN_LOGIN) {

        // Login
        composable(route = SCREEN_LOGIN) { LoginScreen(mainNavController) }

        // Signup
        composable(route = SCREEN_SIGNUP) { SignupScreen(mainNavController) }

        // CatMainScreen
        composable(route = SCREEN_CAT_MAIN) { MainScreen(mainNavController) }

        // CatDetail
        composable(route = SCREEN_CAT_DETAIL) {
            val catSelected = CatLocalRepository.selectedCat.value
            if (catSelected != null) {
                CatDetailScreen(mainNavController, catSelected)
            }
        }
    }
}