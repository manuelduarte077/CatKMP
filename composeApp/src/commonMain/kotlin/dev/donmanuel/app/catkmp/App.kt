package dev.donmanuel.app.catkmp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column (
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Hello World")

            Card {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text("Hello World")

                    Row {
                        Text(text = "Hello World", modifier = Modifier.padding(8.dp))
                        Text(text = "Hello World", modifier = Modifier.padding(8.dp))
                        Text(text = "Hello World", modifier = Modifier.padding(8.dp))
                        Text(text = "Hello World", modifier = Modifier.padding(8.dp))
                    }
                }
            }
        }


    }
}