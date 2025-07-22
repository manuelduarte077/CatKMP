package dev.donmanuel.app.catkmp.domain.usecase

import dev.donmanuel.app.catkmp.domain.model.SignupRequest
import dev.donmanuel.app.catkmp.domain.repository.SignupRepository
import dev.donmanuel.app.catkmp.domain.repository.UiState

class SignupUseCase(private val repository: SignupRepository) {
    suspend operator fun invoke(signupRequest: SignupRequest): UiState {
        if (signupRequest.name.isBlank()) return UiState.Error("Name required")
        if (signupRequest.user.isBlank()) return UiState.Error("User required")
        if (signupRequest.email.isBlank()) return UiState.Error("Email required")
        if (signupRequest.password.isBlank()) return UiState.Error("Password required")
        if (!signupRequest.email.contains("@")) return UiState.Error("Invalid email")
        if (signupRequest.password.length < 4) return UiState.Error("Password must be at least 4 characters")
        return repository.signup(signupRequest)
    }
}