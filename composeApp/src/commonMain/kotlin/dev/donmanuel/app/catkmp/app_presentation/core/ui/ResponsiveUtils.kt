package dev.donmanuel.app.catkmp.app_presentation.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Utility functions for responsive design across different platforms
 */

enum class Platform {
    ANDROID, IOS, DESKTOP, WEB
}

enum class ScreenSize {
    SMALL, MEDIUM, LARGE, EXTRA_LARGE
}

@Composable
fun getScreenSize(): ScreenSize {
    val configuration = LocalConfiguration.current
    
    // Get the actual screen width in dp
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp
    
    // For desktop, ensure minimum size to avoid mobile-like appearance
    // If screen width is very small (like in some desktop environments), default to LARGE
    val effectiveWidth = when {
        screenWidthDp < 400.dp && screenHeightDp >= 600.dp -> 1024.dp // Default to tablet/desktop size
        screenWidthDp < 400.dp -> 800.dp // Default to medium size
        else -> screenWidthDp
    }
    
    return when {
        effectiveWidth < 600.dp -> ScreenSize.SMALL
        effectiveWidth < 960.dp -> ScreenSize.MEDIUM
        effectiveWidth < 1280.dp -> ScreenSize.LARGE
        else -> ScreenSize.EXTRA_LARGE
    }
}

@Composable
fun isDesktop(): Boolean {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp
    
    // Desktop detection: wide screen with good aspect ratio
    // Also consider density to avoid false positives on high-DPI mobile devices
    val density = LocalDensity.current
    val isHighDensity = density.density > 2.0
    
    return if (isHighDensity) {
        // For high-DPI screens, require larger dimensions
        screenWidthDp >= 1200.dp && screenHeightDp >= 900.dp
    } else {
        // For standard density screens
        screenWidthDp >= 1024.dp && screenHeightDp >= 768.dp
    }
}

@Composable
fun isTablet(): Boolean {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp
    
    // Tablet detection: medium to large screen
    return (screenWidthDp >= 600.dp && screenWidthDp < 1024.dp) ||
           (screenHeightDp >= 600.dp && screenHeightDp < 1024.dp)
}

@Composable
fun isMobile(): Boolean {
    return !isDesktop() && !isTablet()
}

/**
 * Responsive padding values based on screen size and platform
 */
@Composable
fun getResponsivePadding(): ResponsivePadding {
    return when {
        isDesktop() -> ResponsivePadding(
            horizontal = 80.dp,  // Much larger for desktop
            vertical = 60.dp,
            betweenElements = 32.dp
        )
        isTablet() -> ResponsivePadding(
            horizontal = 48.dp,
            vertical = 40.dp,
            betweenElements = 24.dp
        )
        else -> {
            // Mobile
            when (getScreenSize()) {
                ScreenSize.SMALL -> ResponsivePadding(
                    horizontal = 16.dp,
                    vertical = 24.dp,
                    betweenElements = 16.dp
                )
                ScreenSize.MEDIUM -> ResponsivePadding(
                    horizontal = 32.dp,
                    vertical = 32.dp,
                    betweenElements = 20.dp
                )
                else -> ResponsivePadding(
                    horizontal = 40.dp,
                    vertical = 36.dp,
                    betweenElements = 22.dp
                )
            }
        }
    }
}

/**
 * Responsive text sizes based on screen size and platform
 */
@Composable
fun getResponsiveTextSizes(): ResponsiveTextSizes {
    return when {
        isDesktop() -> ResponsiveTextSizes(
            title = 40.sp,      // Much larger for desktop
            subtitle = 32.sp,
            body = 24.sp,
            caption = 20.sp
        )
        isTablet() -> ResponsiveTextSizes(
            title = 32.sp,
            subtitle = 24.sp,
            body = 18.sp,
            caption = 16.sp
        )
        else -> {
            // Mobile
            when (getScreenSize()) {
                ScreenSize.SMALL -> ResponsiveTextSizes(
                    title = 24.sp,
                    subtitle = 18.sp,
                    body = 14.sp,
                    caption = 12.sp
                )
                ScreenSize.MEDIUM -> ResponsiveTextSizes(
                    title = 28.sp,
                    subtitle = 20.sp,
                    body = 16.sp,
                    caption = 14.sp
                )
                else -> ResponsiveTextSizes(
                    title = 30.sp,
                    subtitle = 22.sp,
                    body = 17.sp,
                    caption = 15.sp
                )
            }
        }
    }
}

/**
 * Responsive button sizes based on screen size and platform
 */
@Composable
fun getResponsiveButtonSizes(): ResponsiveButtonSizes {
    return when {
        isDesktop() -> ResponsiveButtonSizes(
            height = 80.dp,     // Much larger for desktop
            cornerRadius = 24.dp
        )
        isTablet() -> ResponsiveButtonSizes(
            height = 64.dp,
            cornerRadius = 16.dp
        )
        else -> {
            // Mobile
            when (getScreenSize()) {
                ScreenSize.SMALL -> ResponsiveButtonSizes(
                    height = 48.dp,
                    cornerRadius = 8.dp
                )
                ScreenSize.MEDIUM -> ResponsiveButtonSizes(
                    height = 56.dp,
                    cornerRadius = 12.dp
                )
                else -> ResponsiveButtonSizes(
                    height = 60.dp,
                    cornerRadius = 14.dp
                )
            }
        }
    }
}

/**
 * Get maximum content width for desktop to avoid overly wide layouts
 */
@Composable
fun getMaxContentWidth(): androidx.compose.ui.unit.Dp {
    return when {
        isDesktop() -> 800.dp  // Limit width on desktop for better readability
        isTablet() -> 600.dp   // Limit width on tablet
        else -> 400.dp         // Full width on mobile
    }
}

data class ResponsivePadding(
    val horizontal: androidx.compose.ui.unit.Dp,
    val vertical: androidx.compose.ui.unit.Dp,
    val betweenElements: androidx.compose.ui.unit.Dp
)

data class ResponsiveTextSizes(
    val title: androidx.compose.ui.unit.TextUnit,
    val subtitle: androidx.compose.ui.unit.TextUnit,
    val body: androidx.compose.ui.unit.TextUnit,
    val caption: androidx.compose.ui.unit.TextUnit
)

data class ResponsiveButtonSizes(
    val height: androidx.compose.ui.unit.Dp,
    val cornerRadius: androidx.compose.ui.unit.Dp
) 