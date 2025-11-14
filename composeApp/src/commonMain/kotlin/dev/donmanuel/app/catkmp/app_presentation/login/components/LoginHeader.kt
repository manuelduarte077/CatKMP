package dev.donmanuel.app.catkmp.app_presentation.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.donmanuel.app.catkmp.app_presentation.core.ui.ResponsiveText
import dev.donmanuel.app.catkmp.app_presentation.core.ui.ResponsiveTextSizes
import dev.donmanuel.app.catkmp.app_presentation.core.ui.getResponsiveTextSizes
import dev.donmanuel.app.catkmp.app_presentation.core.ui.isDesktop
import dev.donmanuel.app.catkmp.app_presentation.core.ui.isTablet
import dev.donmanuel.app.catkmp.resources.Res
import dev.donmanuel.app.catkmp.resources.img_cat
import org.jetbrains.compose.resources.painterResource

@Composable
fun LoginHeader() {
    val isDesktop = isDesktop()
    val isTablet = isTablet()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            modifier = Modifier
                .size(if (isDesktop) 200.dp else if (isTablet) 150.dp else 120.dp)
                .padding(top = if (isDesktop) 48.dp else 24.dp),
            painter = painterResource(Res.drawable.img_cat),
            contentDescription = "Cat Logo"
        )

        ResponsiveText(
            text = "Welcome Back!",
            textAlign = TextAlign.Center
        )

        ResponsiveText(
            text = "Sign in to your account to continue",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
} 