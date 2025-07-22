package dev.donmanuel.app.catkmp.data.remote.repository

import dev.donmanuel.app.catkmp.domain.model.LoginRequest
import dev.donmanuel.app.catkmp.domain.model.LoginResponse
import dev.donmanuel.app.catkmp.domain.repository.LoginRepository
import dev.donmanuel.app.catkmp.domain.repository.UiState
import kotlinx.coroutines.delay

class LoginRepositoryData : LoginRepository {

    override suspend fun login(loginRequest: LoginRequest): UiState {
        return try {

            delay(2000)

            if (loginRequest.user == "admin" && loginRequest.pass == "secret") {

                UiState.Success(
                    result = LoginResponse(
                        "Manuel Duarte",
                        "Admin",
                        "BearerTokenSimulation: GYUHjb6NB6V4Ev6vbfcv3vb6N8yn7"
                    )
                )
            } else {
                UiState.Error(message = "User or password incorrect")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            UiState.Error(message = e.message.toString())
        }
    }
}