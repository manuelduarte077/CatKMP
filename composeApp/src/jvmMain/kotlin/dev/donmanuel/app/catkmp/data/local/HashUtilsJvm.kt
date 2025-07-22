package dev.donmanuel.app.catkmp.data.local

import java.security.MessageDigest

actual object HashUtils {
    actual fun sha256(input: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(input.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
} 