package dev.donmanuel.app.catkmp.app_presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.donmanuel.app.catkmp.AlertDialog
import dev.donmanuel.app.catkmp.app_presentation.core.navigation.SCREEN_CAT_MAIN
import dev.donmanuel.app.catkmp.app_presentation.core.navigation.SCREEN_LOGIN
import dev.donmanuel.app.catkmp.app_presentation.core.navigation.SCREEN_SIGNUP
import dev.donmanuel.app.catkmp.app_presentation.core.ui.*
import dev.donmanuel.app.catkmp.data.local.SettingsUtils
import dev.donmanuel.app.catkmp.data.local.SettingsUtils.KEY_TOKEN
import dev.donmanuel.app.catkmp.domain.model.LoginRequest
import dev.donmanuel.app.catkmp.domain.model.LoginResponse
import dev.donmanuel.app.catkmp.domain.repository.UiState
import com.russhwolf.settings.get
import dev.donmanuel.app.catkmp.resources.Res
import dev.donmanuel.app.catkmp.resources.ic_visibility
import dev.donmanuel.app.catkmp.resources.ic_visibility_off
import dev.donmanuel.app.catkmp.resources.img_cat
import dev.donmanuel.app.catkmp.resources.loading
import dev.donmanuel.app.catkmp.resources.login
import dev.donmanuel.app.catkmp.resources.login_password
import dev.donmanuel.app.catkmp.resources.login_user
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

/**
 * Displays the responsive login screen UI, handling user authentication and navigation.
 *
 * Features:
 * - Responsive design that adapts to different screen sizes
 * - Modern Material 3 design with improved spacing
 * - Better visual hierarchy and typography
 * - Improved accessibility and user experience
 * - Cross-platform compatibility (Android, iOS, Desktop, Web)
 */
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
    val textSizes = getResponsiveTextSizes()
    val buttonSizes = getResponsiveButtonSizes()
    val isDesktop = isDesktop()
    val isTablet = isTablet()
    
    val keyboardController = LocalSoftwareKeyboardController.current
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
            // Logo and title section
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(if (isDesktop) 200.dp else if (isTablet) 150.dp else 120.dp)
                        .padding(top = if (isDesktop) 48.dp else 24.dp),
                    painter = painterResource(Res.drawable.img_cat),
                    contentDescription = "Cat Logo"
                )
                
                Text(
                    text = "Welcome Back!",
                    fontSize = textSizes.title,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                
                Text(
                    text = "Sign in to your account to continue",
                    fontSize = textSizes.body,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }

            // Form section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    // Username field
                    OutlinedTextField(
                        value = textUser,
                        onValueChange = { if (it.length <= 20) textUser = it },
                        label = { Text("Username") },
                        placeholder = { Text("Enter your username") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        )
                    )

                    // Password field
                    OutlinedTextField(
                        value = textPass,
                        onValueChange = { if (it.length <= 20) textPass = it },
                        label = { Text("Password") },
                        placeholder = { Text("Enter your password") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true,
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    painter = painterResource(
                                        if (passwordVisible) Res.drawable.ic_visibility 
                                        else Res.drawable.ic_visibility_off
                                    ),
                                    contentDescription = if (passwordVisible) "Hide password" else "Show password"
                                )
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        )
                    )

                    // Login button
                    Button(
                        onClick = {
                            keyboardController?.hide()
                            loginViewModel.getLogin(LoginRequest(user = textUser, pass = textPass))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(buttonSizes.height),
                        shape = RoundedCornerShape(buttonSizes.cornerRadius),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = stringResource(Res.string.login),
                            fontSize = textSizes.body,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    // Sign up link
                    TextButton(
                        onClick = { navController.navigate(SCREEN_SIGNUP) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Don't have an account? Sign up",
                            fontSize = textSizes.caption,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

        // Loading overlay
        when (stateLogin) {
            UiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.6f)),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            CircularProgressIndicator(
                                strokeWidth = 4.dp,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = stringResource(Res.string.loading),
                                fontSize = textSizes.body,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
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
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(dialogTitle) },
                text = { Text(dialogMessage) },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}
