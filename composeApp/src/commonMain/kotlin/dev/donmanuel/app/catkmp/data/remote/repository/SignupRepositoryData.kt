package dev.donmanuel.app.catkmp.data.remote.repository

import dev.donmanuel.app.catkmp.domain.model.SignupRequest
import dev.donmanuel.app.catkmp.domain.model.SignupResponse
import dev.donmanuel.app.catkmp.domain.repository.SignupRepository
import dev.donmanuel.app.catkmp.domain.repository.UiState
import kotlinx.coroutines.delay
import java.util.UUID

class SignupRepositoryData : SignupRepository {
    override suspend fun signup(signupRequest: SignupRequest): UiState {
        return try {
            delay(2000)
            if (signupRequest.user == "taken" || signupRequest.email == "taken@email.com") {
                UiState.Error(message = "User or email already exists")
            } else {
                UiState.Success(
                    result = SignupResponse(
                        userId = UUID.randomUUID().toString(),
                        name = signupRequest.name,
                        user = signupRequest.user,
                        email = signupRequest.email
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            UiState.Error(message = e.message.toString())
        }
    }
} 