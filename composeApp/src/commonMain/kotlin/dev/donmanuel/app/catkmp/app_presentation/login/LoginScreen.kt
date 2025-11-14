package dev.donmanuel.app.catkmp.app_presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dev.donmanuel.app.catkmp.app_presentation.core.navigation.SCREEN_CAT_MAIN
import dev.donmanuel.app.catkmp.app_presentation.core.navigation.SCREEN_LOGIN
import dev.donmanuel.app.catkmp.app_presentation.core.navigation.SCREEN_SIGNUP
import dev.donmanuel.app.catkmp.app_presentation.core.ui.getResponsivePadding
import dev.donmanuel.app.catkmp.app_presentation.core.ui.isDesktop
import dev.donmanuel.app.catkmp.app_presentation.login.components.*
import dev.donmanuel.app.catkmp.data.local.SettingsUtils
import dev.donmanuel.app.catkmp.data.local.SettingsUtils.KEY_TOKEN
import dev.donmanuel.app.catkmp.domain.model.LoginRequest
import dev.donmanuel.app.catkmp.domain.model.LoginResponse
import dev.donmanuel.app.catkmp.domain.repository.UiState
import com.russhwolf.settings.get
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@OptIn(KoinExperimentalAPI::class)
@Composable
fun LoginScreen(navController: NavController) {
    // ViewModel
    val loginViewModel = koinViewModel<LoginViewModel>()
    val stateLogin by loginViewModel.stateLogin.collectAsState()

    // Input variables
    var textUser by remember { mutableStateOf("") }
    var textPass by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    // Dialog state
    var showDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf("") }

    // Responsive values
    val padding = getResponsivePadding()
    val isDesktop = isDesktop()

    val scrollState = rememberScrollState()

    // Auto-navigate if already logged in
    if (SettingsUtils.data[KEY_TOKEN, ""].isNotEmpty()) {
        LaunchedEffect(Unit) {
            navController.navigate(SCREEN_CAT_MAIN) {
                popUpTo(SCREEN_LOGIN) { inclusive = true }
            }
        }
        return
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Main content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(
                    horizontal = if (isDesktop) padding.horizontal * 2 else padding.horizontal,
                    vertical = padding.vertical
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(padding.betweenElements)
        ) {
            ResponsiveWrapper {
                LoginHeader()
                LoginForm(
                    textUser = textUser,
                    textPass = textPass,
                    passwordVisible = passwordVisible,
                    onUserChange = { textUser = it },
                    onPasswordChange = { textPass = it },
                    onPasswordVisibilityToggle = { passwordVisible = !passwordVisible },
                    onLoginClick = {
                        loginViewModel.getLogin(LoginRequest(user = textUser, pass = textPass))
                    },
                    onSignupClick = {
                        navController.navigate(SCREEN_SIGNUP)
                    }
                )
            }
        }

        // Loading overlay
        when (stateLogin) {
            UiState.Loading -> {
                LoadingOverlay()
            }

            is UiState.Success -> {
                val loginResponse = (stateLogin as UiState.Success).result as LoginResponse
                LaunchedEffect(Unit) {
                    SettingsUtils.data.putString(KEY_TOKEN, loginResponse.token)
                    loginViewModel.clearStateLogin()
                    navController.navigate(SCREEN_CAT_MAIN) {
                        popUpTo(SCREEN_LOGIN) { inclusive = true }
                    }
                }
            }

            is UiState.Error -> {
                val error = stateLogin as UiState.Error
                LaunchedEffect(Unit) {
                    showDialog = true
                    dialogTitle = "Error"
                    dialogMessage = error.message ?: "An unknown error occurred"
                    loginViewModel.clearStateLogin()
                }
            }

            else -> {}
        }

        // Error dialog
        if (showDialog) {
            ErrorDialog(
                title = dialogTitle,
                message = dialogMessage,
                onDismiss = { showDialog = false }
            )
        }
    }
}
