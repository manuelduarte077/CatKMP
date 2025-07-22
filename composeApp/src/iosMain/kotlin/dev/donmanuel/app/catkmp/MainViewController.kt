package dev.donmanuel.app.catkmp

import androidx.compose.ui.window.ComposeUIViewController
import dev.donmanuel.app.catkmp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) {
    App()
}