package dev.donmanuel.app.catkmp.data.remote.repository

import dev.donmanuel.app.catkmp.data.local.LocalDatabase
import dev.donmanuel.app.catkmp.data.local.HashUtils
import dev.donmanuel.app.catkmp.domain.model.LoginRequest
import dev.donmanuel.app.catkmp.domain.model.LoginResponse
import dev.donmanuel.app.catkmp.domain.repository.LoginRepository
import dev.donmanuel.app.catkmp.domain.repository.UiState
import kotlinx.coroutines.delay

class LoginRepositoryData(private val localDatabase: LocalDatabase) : LoginRepository {
    override suspend fun login(loginRequest: LoginRequest): UiState {
        return try {
            delay(1000)
            val user = localDatabase.getUserByUsername(loginRequest.user)
            if (user == null) {
                UiState.Error(message = "User not found")
            } else if (user.password != HashUtils.sha256(loginRequest.pass)) {
                UiState.Error(message = "Incorrect password")
            } else {
                UiState.Success(
                    result = LoginResponse(
                        name = user.name,
                        rol = "User",
                        token = "LocalTokenSimulation"
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            UiState.Error(message = e.message.toString())
        }
    }
}