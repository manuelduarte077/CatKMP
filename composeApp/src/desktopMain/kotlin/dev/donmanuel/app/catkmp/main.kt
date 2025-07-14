package dev.donmanuel.app.catkmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "catkmp",
    ) {
        App()
    }
}