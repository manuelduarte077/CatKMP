package dev.donmanuel.app.catkmp

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import org.koin.core.module.Module
import dev.donmanuel.app.catkmp.data.local.DatabaseDriverFactory
import org.koin.dsl.module

class JsDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createDriver(): app.cash.sqldelight.db.SqlDriver {
        // For JS, we'll use a simple in-memory implementation
        // In a real app, you might want to use IndexedDB or localStorage
        throw NotImplementedError("JS database driver not fully implemented - consider using IndexedDB")
    }
}

actual val targetModule: Module = module {
    single<DatabaseDriverFactory> { JsDatabaseDriverFactory() }
}

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