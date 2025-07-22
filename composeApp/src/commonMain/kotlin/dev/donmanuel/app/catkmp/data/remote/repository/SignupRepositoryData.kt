package dev.donmanuel.app.catkmp.data.remote.repository

import dev.donmanuel.app.catkmp.data.local.LocalDatabase
import dev.donmanuel.app.catkmp.domain.model.SignupRequest
import dev.donmanuel.app.catkmp.domain.model.SignupResponse
import dev.donmanuel.app.catkmp.domain.repository.SignupRepository
import dev.donmanuel.app.catkmp.domain.repository.UiState
import kotlinx.coroutines.delay
import java.util.UUID

class SignupRepositoryData(private val localDatabase: LocalDatabase) : SignupRepository {
    /**
     * Registers a new user if the username and email are not already in use.
     *
     * Introduces a delay before checking for existing users in the local database. If a duplicate username or email is found, returns an error state. Otherwise, creates a new user with a unique ID, stores it in the database, and returns a success state with the user's details. Returns an error state if an exception occurs during the process.
     *
     * @param signupRequest The signup information for the new user.
     * @return A [UiState] representing success with user details or an error with a message.
     */
    override suspend fun signup(signupRequest: SignupRequest): UiState {
        return try {
            delay(2000)
            // Verificar si el usuario o email ya existen localmente
            val existing = localDatabase.getAllUsers().any {
                it.user == signupRequest.user || it.email == signupRequest.email
            }
            if (existing) {
                UiState.Error(message = "User or email already exists")
            } else {
                val userId = UUID.randomUUID().toString()
                localDatabase.insertUser(signupRequest, userId)
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