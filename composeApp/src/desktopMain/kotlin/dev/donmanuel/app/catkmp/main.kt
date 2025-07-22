package dev.donmanuel.app.catkmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.donmanuel.app.catkmp.di.initKoin

fun main() = application {

    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "Cat KMP",
    ) {
        App()
    }
}