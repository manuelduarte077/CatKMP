package dev.donmanuel.app.catkmp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.donmanuel.app.catkmp.data.local.AndroidDatabaseDriverFactory
import dev.donmanuel.app.catkmp.data.local.DatabaseDriverFactory
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext

@Composable
actual fun AlertDialog(
    title: String,
    message: String,
    onDismissRequest: () -> Unit
) {

    Column(verticalArrangement = Arrangement.Center) {
        androidx.compose.material3.AlertDialog(
            shape = RoundedCornerShape(8.dp),
            title = {
                Text(text = title, fontWeight = FontWeight.Bold)
            },
            text = { Text(text = message, fontWeight = FontWeight.SemiBold) },
            confirmButton = {
                Button(
                    shape = RoundedCornerShape(8.dp),
                    onClick = { onDismissRequest() }) { Text("Aceptar") }
            },
            onDismissRequest = {}
        )
    }
}

actual val targetModule = module {
    single<DatabaseDriverFactory> {
        AndroidDatabaseDriverFactory(androidContext())
    }
}