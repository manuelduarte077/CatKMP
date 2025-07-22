package dev.donmanuel.app.catkmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import dev.donmanuel.app.catkmp.app_presentation.core.navigation.NavigationWrapper
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        NavigationWrapper()
    }
}