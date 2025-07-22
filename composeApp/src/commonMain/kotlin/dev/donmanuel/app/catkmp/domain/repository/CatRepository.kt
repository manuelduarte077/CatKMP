package dev.donmanuel.app.catkmp.domain.repository

interface CatRepository {

    suspend fun getCatList(): UiState

}