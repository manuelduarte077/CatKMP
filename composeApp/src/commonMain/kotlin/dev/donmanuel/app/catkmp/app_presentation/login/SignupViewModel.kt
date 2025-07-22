package dev.donmanuel.app.catkmp.app_presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.donmanuel.app.catkmp.domain.model.SignupRequest
import dev.donmanuel.app.catkmp.domain.repository.UiState
import dev.donmanuel.app.catkmp.domain.usecase.SignupUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignupViewModel(private val signupUseCase: SignupUseCase) : ViewModel() {
    private val _stateSignup = MutableStateFlow<UiState>(UiState.Init)
    val stateSignup: StateFlow<UiState> = _stateSignup.asStateFlow()

    /**
     * Initiates the user signup process and updates the signup UI state.
     *
     * Sets the state to loading, then asynchronously invokes the signup use case with the provided request.
     * The result of the signup operation is reflected in the UI state.
     *
     * @param signupRequest The user information required for signup.
     */
    fun signup(signupRequest: SignupRequest) {
        _stateSignup.value = UiState.Loading
        viewModelScope.launch {
            _stateSignup.value = signupUseCase(signupRequest)
        }
    }

    /**
 * Resets the signup UI state to its initial value asynchronously.
 */
fun clearStateSignup() = viewModelScope.launch { _stateSignup.value = UiState.Init }
} 