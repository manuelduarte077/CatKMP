package dev.donmanuel.app.catkmp

import androidx.compose.runtime.Composable
import org.koin.core.module.Module
import dev.donmanuel.app.catkmp.data.local.DatabaseDriverFactory
import org.koin.dsl.module

class JsDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createDriver(): app.cash.sqldelight.db.SqlDriver {
        throw NotImplementedError("No database driver for JS implemented")
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
    // Stub: No-op para JS
} 