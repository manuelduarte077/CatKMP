package dev.donmanuel.app.catkmp.domain.usecase

import dev.donmanuel.app.catkmp.domain.model.LoginRequest
import dev.donmanuel.app.catkmp.domain.repository.LoginRepository
import dev.donmanuel.app.catkmp.domain.repository.UiState

class LoginUseCase(private val repository: LoginRepository) {

    suspend operator fun invoke(loginRequest: LoginRequest): UiState {

        val user = loginRequest.user
        val pass = loginRequest.pass

        return when {
            user.isEmpty() -> UiState.Error("User required")
            pass.isEmpty() -> UiState.Error("Password required")
            else -> repository.login(loginRequest)
        }
    }
}