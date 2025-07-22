package dev.donmanuel.app.catkmp.data.local

expect object HashUtils {
    fun sha256(input: String): String
} 