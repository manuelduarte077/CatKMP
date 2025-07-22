package dev.donmanuel.app.catkmp.app_presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dev.donmanuel.app.catkmp.app_presentation.core.ui.getResponsivePadding
import dev.donmanuel.app.catkmp.app_presentation.core.ui.isDesktop
import dev.donmanuel.app.catkmp.app_presentation.core.ui.LoadingOverlay
import dev.donmanuel.app.catkmp.app_presentation.core.ui.ErrorDialog
import dev.donmanuel.app.catkmp.app_presentation.core.ui.SuccessDialog
import dev.donmanuel.app.catkmp.app_presentation.login.components.*
import dev.donmanuel.app.catkmp.domain.model.SignupRequest
import dev.donmanuel.app.catkmp.domain.repository.UiState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignupScreen(navController: NavController) {
    val signupViewModel = koinViewModel<SignupViewModel>()
    val stateSignup by signupViewModel.stateSignup.collectAsState()

    // Input variables
    var name by remember { mutableStateOf("") }
    var user by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    // Dialog state
    var showDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf("") }

    // Responsive values
    val padding = getResponsivePadding()
    val isDesktop = isDesktop()
    
    val scrollState = rememberScrollState()

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
                SignupHeader()
                SignupForm(
                    name = name,
                    user = user,
                    email = email,
                    password = password,
                    passwordVisible = passwordVisible,
                    onNameChange = { name = it },
                    onUserChange = { user = it },
                    onEmailChange = { email = it },
                    onPasswordChange = { password = it },
                    onPasswordVisibilityToggle = { passwordVisible = !passwordVisible },
                    onSignupClick = {
                        signupViewModel.signup(SignupRequest(name, user, email, password))
                    },
                    onLoginClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }

    when (stateSignup) {
        UiState.Loading -> {
            LoadingOverlay(message = "Creating your account...")
        }

        is UiState.Success -> {
            LaunchedEffect(Unit) {
                showDialog = true
                dialogTitle = "Success!"
                dialogMessage = "Your account has been created successfully. You can now sign in."
                signupViewModel.clearStateSignup()
            }
        }

        is UiState.Error -> {
            val error = stateSignup as UiState.Error
            LaunchedEffect(Unit) {
                showDialog = true
                dialogTitle = "Error"
                dialogMessage = error.message ?: "An unknown error occurred"
                signupViewModel.clearStateSignup()
            }
        }

        else -> {}
    }

    // Success/Error dialog
    if (showDialog) {
        if (dialogTitle == "Success!") {
            SuccessDialog(
                title = dialogTitle,
                message = dialogMessage,
                onDismiss = {
                    showDialog = false
                    navController.popBackStack()
                },
                onConfirm = {
                    showDialog = false
                    navController.popBackStack()
                },
                confirmText = "Sign In"
            )
        } else {
            ErrorDialog(
                title = dialogTitle,
                message = dialogMessage,
                onDismiss = { showDialog = false }
            )
        }
    }
}