package dev.donmanuel.app.catkmp.app_presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.donmanuel.app.catkmp.AlertDialog
import dev.donmanuel.app.catkmp.app_presentation.core.navigation.SCREEN_CAT_MAIN
import dev.donmanuel.app.catkmp.app_presentation.core.navigation.SCREEN_LOGIN
import dev.donmanuel.app.catkmp.app_presentation.core.navigation.SCREEN_SIGNUP
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

    var showDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    if (SettingsUtils.data[KEY_TOKEN, ""].isNotEmpty()) {
        navController.navigate(SCREEN_CAT_MAIN) {
            popUpTo(SCREEN_LOGIN) { inclusive = true }
        }
    } else {
        Box {
            Column(
                modifier = Modifier.fillMaxSize()
                    .statusBarsPadding()
                    .padding(start = 14.dp, top = 24.dp, end = 14.dp, bottom = 12.dp)
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 12.dp)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(Res.drawable.img_cat),
                    contentDescription = null
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                    value = textUser,
                    placeholder = { Text(text = stringResource(Res.string.login_user)) },
                    shape = RoundedCornerShape(8.dp),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        capitalization = KeyboardCapitalization.Characters,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = { value ->
                        if (value.length <= 20) {
                            textUser = value
                        }
                    }
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                    value = textPass,
                    placeholder = { Text(text = stringResource(Res.string.login_password)) },
                    shape = RoundedCornerShape(8.dp),
                    maxLines = 1,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val icon =
                            if (passwordVisible) Res.drawable.ic_visibility else Res.drawable.ic_visibility_off
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(painter = painterResource(icon), contentDescription = null)
                        }
                    },
                    onValueChange = { value ->
                        if (value.length <= 20) {
                            textPass = value
                        }
                    }
                )

                Button(
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = 12.dp),
                    onClick = {
                        keyboardController?.hide()
                        loginViewModel.getLogin(LoginRequest(user = textUser, pass = textPass))
                    },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = stringResource(Res.string.login),
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { navController.navigate(SCREEN_SIGNUP) }
                ) {
                    Text("Create account")
                }
            }

            // States
            when (stateLogin) {
                UiState.Init -> {}
                UiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.7f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Text(stringResource(Res.string.loading))
                            CircularProgressIndicator(strokeWidth = 4.dp)
                        }
                    }
                }

                is UiState.Success -> {

                    val loginResponse = ((stateLogin as UiState.Success).result as LoginResponse)

                    // Save token
                    SettingsUtils.data.putString(KEY_TOKEN, loginResponse.token)

                    // Clear state login
                    loginViewModel.clearStateLogin()

                    // Navigate to main
                    navController.navigate(SCREEN_CAT_MAIN) {
                        popUpTo(SCREEN_LOGIN) { inclusive = true }
                    }
                }

                is UiState.Error -> {

                    val loginResponse = stateLogin as UiState.Error
                    showDialog = true
                    dialogTitle = "Atención"
                    dialogMessage = loginResponse.message ?: ""

                    loginViewModel.clearStateLogin()
                }
            }

            if (showDialog) {
                Box {
                    AlertDialog(dialogTitle, dialogMessage) {
                        showDialog = false
                    }
                }
            }
        }
    }
}
