package dev.donmanuel.app.catkmp.app_presentation.login.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import dev.donmanuel.app.catkmp.app_presentation.core.ui.*
import dev.donmanuel.app.catkmp.resources.Res
import dev.donmanuel.app.catkmp.resources.ic_visibility
import dev.donmanuel.app.catkmp.resources.ic_visibility_off
import org.jetbrains.compose.resources.painterResource

@Composable
fun SignupForm(
    name: String,
    user: String,
    email: String,
    password: String,
    passwordVisible: Boolean,
    onNameChange: (String) -> Unit,
    onUserChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordVisibilityToggle: () -> Unit,
    onSignupClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    val buttonSizes = getResponsiveButtonSizes()
    val keyboardController = LocalSoftwareKeyboardController.current

    ResponsiveCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Name field
            ResponsiveTextField(
                value = name,
                onValueChange = onNameChange,
                label = { Text("Full Name") },
                placeholder = { Text("Enter your full name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
            )

            // Username field
            ResponsiveTextField(
                value = user,
                onValueChange = onUserChange,
                label = { Text("Username") },
                placeholder = { Text("Choose a username") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
            )

            // Email field
            ResponsiveTextField(
                value = email,
                onValueChange = onEmailChange,
                label = { Text("Email") },
                placeholder = { Text("Enter your email address") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
            )

            // Password field
            ResponsiveTextField(
                value = password,
                onValueChange = onPasswordChange,
                label = { Text("Password") },
                placeholder = { Text("Create a password") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                trailingIcon = {
                    IconButton(onClick = onPasswordVisibilityToggle) {
                        Icon(
                            painter = painterResource(
                                if (passwordVisible) Res.drawable.ic_visibility
                                else Res.drawable.ic_visibility_off
                            ),
                            contentDescription = if (passwordVisible) "Hide password" else "Show password"
                        )
                    }
                },
            )

            // Register button
            ResponsiveButton(
                onClick = {
                    keyboardController?.hide()
                    onSignupClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(buttonSizes.height),
            ) {
                ResponsiveText(
                    text = "Create Account",
                )
            }

            // Login link
            TextButton(
                onClick = onLoginClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                ResponsiveText(
                    text = "Already have an account? Sign in",
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
} 