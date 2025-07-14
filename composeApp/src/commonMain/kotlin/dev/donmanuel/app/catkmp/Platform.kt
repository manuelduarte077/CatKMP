package dev.donmanuel.app.catkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform