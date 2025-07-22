package dev.donmanuel.app.catkmp.app_presentation.login.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import dev.donmanuel.app.catkmp.app_presentation.core.ui.ResponsiveButton
import dev.donmanuel.app.catkmp.app_presentation.core.ui.ResponsiveCard
import dev.donmanuel.app.catkmp.app_presentation.core.ui.ResponsiveText
import dev.donmanuel.app.catkmp.app_presentation.core.ui.ResponsiveTextField
import dev.donmanuel.app.catkmp.app_presentation.core.ui.getResponsiveButtonSizes
import dev.donmanuel.app.catkmp.resources.Res
import dev.donmanuel.app.catkmp.resources.ic_visibility
import dev.donmanuel.app.catkmp.resources.ic_visibility_off
import dev.donmanuel.app.catkmp.resources.login
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginForm(
    textUser: String,
    textPass: String,
    passwordVisible: Boolean,
    onUserChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordVisibilityToggle: () -> Unit,
    onLoginClick: () -> Unit,
    onSignupClick: () -> Unit
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
            // Username field
            ResponsiveTextField(
                value = textUser,
                onValueChange = { if (it.length <= 20) onUserChange(it) },
                label = { Text("Username") },
                placeholder = { Text("Enter your username") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
            )

            // Password field
            ResponsiveTextField(
                value = textPass,
                onValueChange = { if (it.length <= 20) onPasswordChange(it) },
                label = { Text("Password") },
                placeholder = { Text("Enter your password") },
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

            // Login button
            ResponsiveButton(
                onClick = {
                    keyboardController?.hide()
                    onLoginClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(buttonSizes.height),
            ) {
                ResponsiveText(
                    text = stringResource(Res.string.login),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            // Sign up link
            TextButton(
                onClick = onSignupClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                ResponsiveText(
                    text = "Don't have an account? Sign up",
                )
            }
        }
    }
} 