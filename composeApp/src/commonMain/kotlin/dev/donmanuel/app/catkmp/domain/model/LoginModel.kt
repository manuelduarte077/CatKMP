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

data class SignupRequest(
    val name: String,
    val user: String,
    val email: String,
    val password: String
)

data class SignupResponse(
    val userId: String,
    val name: String,
    val user: String,
    val email: String
)
