package dev.donmanuel.app.catkmp.domain.usecase

import dev.donmanuel.app.catkmp.domain.model.CatModel
import dev.donmanuel.app.catkmp.domain.repository.FavoriteCatRepository

class FavoriteCatUseCase(
    private val repository: FavoriteCatRepository
) {

    suspend fun saveFavorite(catModel: CatModel) = repository.saveFavoriteCat(catModel)

    suspend fun deleteFavoriteCats(idCat: String) = repository.deleteFavoriteCat(idCat)

    suspend fun getFavoriteCats() = repository.getFavoriteCats()
}