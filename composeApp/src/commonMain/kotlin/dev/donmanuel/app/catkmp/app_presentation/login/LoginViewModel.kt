package dev.donmanuel.app.catkmp.app_presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.donmanuel.app.catkmp.domain.model.LoginRequest
import dev.donmanuel.app.catkmp.domain.repository.UiState
import dev.donmanuel.app.catkmp.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _stateLogin = MutableStateFlow<UiState>(UiState.Init)
    val stateLogin: StateFlow<UiState> = _stateLogin.asStateFlow()

    fun getLogin(loginRequest: LoginRequest) {

        _stateLogin.value = UiState.Loading
        viewModelScope.launch {
            _stateLogin.value = loginUseCase(loginRequest)
        }
    }

    fun clearStateLogin() = viewModelScope.launch { _stateLogin.value = UiState.Init }
}