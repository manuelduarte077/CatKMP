package dev.donmanuel.app.catkmp.app_presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.donmanuel.app.catkmp.domain.model.SignupRequest
import dev.donmanuel.app.catkmp.domain.repository.UiState
import org.koin.compose.viewmodel.koinViewModel

/**
 * Displays the signup screen for account creation, handling user input and signup state.
 *
 * Presents a form for entering name, username, email, and password, and manages the signup process using a view model.
 * Shows loading, success, or error dialogs based on the signup result, and allows navigation back to the previous screen.
 *
 * @param navController Controller used for navigation actions.
 */
@Composable
fun SignupScreen(navController: NavController) {
    val signupViewModel = koinViewModel<SignupViewModel>()
    val stateSignup by signupViewModel.stateSignup.collectAsState()

    var name by remember { mutableStateOf("") }
    var user by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Create Account", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
            )
            OutlinedTextField(
                value = user,
                onValueChange = { user = it },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done)
            )
            Button(
                onClick = {
                    signupViewModel.signup(SignupRequest(name, user, email, password))
                },
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)
            ) {
                Text("Register")
            }
            TextButton(onClick = { navController.popBackStack() }) {
                Text("Already have an account? Log in")
            }
        }
        when (stateSignup) {
            UiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is UiState.Success -> {
                showDialog = true
                dialogTitle = "Success"
                dialogMessage = "Account created successfully!"
                signupViewModel.clearStateSignup()
            }
            is UiState.Error -> {
                showDialog = true
                dialogTitle = "Error"
                dialogMessage = (stateSignup as UiState.Error).message ?: "Unknown error"
                signupViewModel.clearStateSignup()
            }
            else -> {}
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(dialogTitle) },
                text = { Text(dialogMessage) },
                confirmButton = {
                    Button(onClick = { showDialog = false }) { Text("OK") }
                }
            )
        }
    }
} 