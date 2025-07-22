package dev.donmanuel.app.catkmp.domain.usecase

import dev.donmanuel.app.catkmp.domain.model.LoginRequest
import dev.donmanuel.app.catkmp.domain.repository.LoginRepository
import dev.donmanuel.app.catkmp.domain.repository.UiState

class LoginUseCase(private val repository: LoginRepository) {

    suspend operator fun invoke(loginRequest: LoginRequest): UiState {

        val user = loginRequest.user
        val pass = loginRequest.pass

        val regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")

        return when {
            user.isEmpty() -> UiState.Error("User required")
            pass.isEmpty() -> UiState.Error("Password required")
            user.isNotEmpty() && user.length < 4 -> UiState.Error("User must be longer than 3 characters")
            pass.isNotEmpty() && pass.length < 4 -> UiState.Error("Password must be longer than 3 characters")
            pass.isNotEmpty() && !regex.matches(pass) -> UiState.Error("Password must contain lower, upper and a number")
            else -> repository.login(loginRequest)
        }
    }
}