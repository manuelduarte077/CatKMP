package dev.donmanuel.app.catkmp.app_presentation.core.ui

import androidx.compose.runtime.Composable
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
    val density = LocalDensity.current
    val screenWidth = (density.density * 1000).dp // Approximate screen width
    
    return when {
        screenWidth < 600.dp -> ScreenSize.SMALL
        screenWidth < 960.dp -> ScreenSize.MEDIUM
        screenWidth < 1280.dp -> ScreenSize.LARGE
        else -> ScreenSize.EXTRA_LARGE
    }
}

@Composable
fun isTablet(): Boolean {
    return getScreenSize() in listOf(ScreenSize.LARGE, ScreenSize.EXTRA_LARGE)
}

@Composable
fun isDesktop(): Boolean {
    return getScreenSize() == ScreenSize.EXTRA_LARGE
}

@Composable
fun isMobile(): Boolean {
    return getScreenSize() in listOf(ScreenSize.SMALL, ScreenSize.MEDIUM)
}

/**
 * Responsive padding values based on screen size
 */
@Composable
fun getResponsivePadding(): ResponsivePadding {
    return when (getScreenSize()) {
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
        ScreenSize.LARGE -> ResponsivePadding(
            horizontal = 48.dp,
            vertical = 40.dp,
            betweenElements = 24.dp
        )
        ScreenSize.EXTRA_LARGE -> ResponsivePadding(
            horizontal = 64.dp,
            vertical = 48.dp,
            betweenElements = 28.dp
        )
    }
}

/**
 * Responsive text sizes based on screen size
 */
@Composable
fun getResponsiveTextSizes(): ResponsiveTextSizes {
    return when (getScreenSize()) {
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
        ScreenSize.LARGE -> ResponsiveTextSizes(
            title = 32.sp,
            subtitle = 24.sp,
            body = 18.sp,
            caption = 16.sp
        )
        ScreenSize.EXTRA_LARGE -> ResponsiveTextSizes(
            title = 36.sp,
            subtitle = 28.sp,
            body = 20.sp,
            caption = 18.sp
        )
    }
}

/**
 * Responsive button sizes based on screen size
 */
@Composable
fun getResponsiveButtonSizes(): ResponsiveButtonSizes {
    return when (getScreenSize()) {
        ScreenSize.SMALL -> ResponsiveButtonSizes(
            height = 48.dp,
            cornerRadius = 8.dp
        )
        ScreenSize.MEDIUM -> ResponsiveButtonSizes(
            height = 56.dp,
            cornerRadius = 12.dp
        )
        ScreenSize.LARGE -> ResponsiveButtonSizes(
            height = 64.dp,
            cornerRadius = 16.dp
        )
        ScreenSize.EXTRA_LARGE -> ResponsiveButtonSizes(
            height = 72.dp,
            cornerRadius = 20.dp
        )
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