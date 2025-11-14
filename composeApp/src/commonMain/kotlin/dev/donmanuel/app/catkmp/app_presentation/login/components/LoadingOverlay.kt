package dev.donmanuel.app.catkmp.app_presentation.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.donmanuel.app.catkmp.app_presentation.core.ui.ResponsiveCard
import dev.donmanuel.app.catkmp.app_presentation.core.ui.ResponsiveText
import dev.donmanuel.app.catkmp.app_presentation.core.ui.getResponsiveTextSizes
import dev.donmanuel.app.catkmp.resources.Res
import dev.donmanuel.app.catkmp.resources.loading
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoadingOverlay() {
    val textSizes = getResponsiveTextSizes()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.6f)),
        contentAlignment = Alignment.Center
    ) {
        ResponsiveCard {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CircularProgressIndicator(
                    strokeWidth = 4.dp,
                    color = MaterialTheme.colorScheme.primary
                )
                ResponsiveText(
                    text = stringResource(Res.string.loading),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
} 