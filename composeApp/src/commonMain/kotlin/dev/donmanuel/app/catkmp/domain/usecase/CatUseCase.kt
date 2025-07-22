package dev.donmanuel.app.catkmp.domain.usecase

import dev.donmanuel.app.catkmp.domain.repository.CatRepository
import dev.donmanuel.app.catkmp.domain.repository.UiState

class CatListUseCase(private val repository: CatRepository) {

    suspend operator fun invoke(): UiState = repository.getCatList()
}