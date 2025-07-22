package dev.donmanuel.app.catkmp

import androidx.compose.runtime.Composable
import org.koin.core.module.Module

expect val targetModule: Module

@Composable
expect fun AlertDialog(
    title: String,
    message: String,
    onDismissRequest: () -> Unit
)