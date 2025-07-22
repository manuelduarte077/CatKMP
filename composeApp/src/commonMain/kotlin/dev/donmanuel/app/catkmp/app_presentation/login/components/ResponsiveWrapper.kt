package dev.donmanuel.app.catkmp.app_presentation.login.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.donmanuel.app.catkmp.app_presentation.core.ui.getMaxContentWidth
import dev.donmanuel.app.catkmp.app_presentation.core.ui.isDesktop

@Composable
fun ResponsiveWrapper(
    content: @Composable () -> Unit
) {
    val isDesktop = isDesktop()

    if (isDesktop) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.width(getMaxContentWidth()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content()
            }
        }
    } else {
        content()
    }
} 