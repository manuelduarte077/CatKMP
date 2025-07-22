package dev.donmanuel.app.catkmp

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(title) },
        text = { Text(message) },
        confirmButton = {
            TextButton(onClick = onDismissRequest) {
                Text("OK")
            }
        }
    )
}

actual val targetModule = module {
    single<DatabaseDriverFactory> {
        DesktopDatabaseDriverFactory()
    }
}