package dev.donmanuel.app.catkmp

import androidx.compose.runtime.Composable
import dev.donmanuel.app.catkmp.data.local.DatabaseDriverFactory
import dev.donmanuel.app.catkmp.data.local.DesktopDatabaseDriverFactory
import org.koin.dsl.module

@Composable
actual fun AlertDialog(
    title: String,
    message: String,
    onDismissRequest: () -> Unit
) {
}

actual val targetModule = module {
    single<DatabaseDriverFactory> {
        DesktopDatabaseDriverFactory()
    }
}