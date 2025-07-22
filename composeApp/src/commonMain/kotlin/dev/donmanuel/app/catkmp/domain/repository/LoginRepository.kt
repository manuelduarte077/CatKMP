package dev.donmanuel.app.catkmp.domain.repository

import dev.donmanuel.app.catkmp.domain.model.LoginRequest

interface LoginRepository {

    /**
 * Performs an asynchronous login operation with the provided credentials.
 *
 * @param loginRequest The login credentials and related information.
 * @return The resulting UI state after attempting to log in.
 */
suspend fun login(loginRequest: LoginRequest): UiState
}

interface SignupRepository {
    /**
 * Performs a user signup operation asynchronously.
 *
 * @param signupRequest The signup request containing user registration details.
 * @return The UI state representing the result of the signup operation.
 */
suspend fun signup(signupRequest: dev.donmanuel.app.catkmp.domain.model.SignupRequest): dev.donmanuel.app.catkmp.domain.repository.UiState
}