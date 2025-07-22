package dev.donmanuel.app.catkmp.data.remote.repository

import dev.donmanuel.app.catkmp.data.local.HashUtils
import dev.donmanuel.app.catkmp.data.local.LocalDatabase
import dev.donmanuel.app.catkmp.domain.model.SignupRequest
import dev.donmanuel.app.catkmp.domain.model.SignupResponse
import dev.donmanuel.app.catkmp.domain.repository.SignupRepository
import dev.donmanuel.app.catkmp.domain.repository.UiState
import kotlinx.coroutines.delay
import java.util.UUID

class SignupRepositoryData(private val localDatabase: LocalDatabase) : SignupRepository {
    override suspend fun signup(signupRequest: SignupRequest): UiState {
        return try {
            delay(2000)
            val existing = localDatabase.getAllUsers().any {
                it.user == signupRequest.user || it.email == signupRequest.email
            }
            if (existing) {
                UiState.Error(message = "User or email already exists")
            } else {
                val userId = UUID.randomUUID().toString()
                localDatabase.insertUser(
                    signupRequest.copy(password = HashUtils.sha256(signupRequest.password)),
                    userId
                )
                UiState.Success(
                    result = SignupResponse(
                        userId = userId,
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