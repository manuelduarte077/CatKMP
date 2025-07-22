package dev.donmanuel.app.catkmp.domain.model

data class LoginRequest(
    val user: String,
    val pass: String,
)

data class LoginResponse(
    val name: String,
    val rol: String,
    val token: String
)
