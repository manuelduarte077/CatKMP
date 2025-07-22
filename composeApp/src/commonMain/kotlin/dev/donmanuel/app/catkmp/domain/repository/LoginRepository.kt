package dev.donmanuel.app.catkmp.domain.repository

import dev.donmanuel.app.catkmp.domain.model.LoginRequest

interface LoginRepository {

    suspend fun login(loginRequest: LoginRequest): UiState
}

interface SignupRepository {
    suspend fun signup(signupRequest: dev.donmanuel.app.catkmp.domain.model.SignupRequest): dev.donmanuel.app.catkmp.domain.repository.UiState
}