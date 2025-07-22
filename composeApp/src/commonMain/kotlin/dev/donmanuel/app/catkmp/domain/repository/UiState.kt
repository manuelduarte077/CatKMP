package dev.donmanuel.app.catkmp.domain.repository

sealed class UiState {
    data object Init : UiState()
    data object Loading : UiState()
    data class Success(val result: Any? = null) : UiState()
    data class Error(val message: String? = "") : UiState()
}