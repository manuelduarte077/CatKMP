package dev.donmanuel.app.catkmp.data.local.repository

import dev.donmanuel.app.catkmp.data.local.LocalDatabase
import dev.donmanuel.app.catkmp.domain.model.CatModel
import dev.donmanuel.app.catkmp.domain.repository.FavoriteCatRepository
import dev.donmanuel.app.catkmp.domain.repository.UiState

class FavoriteCatRepositoryData(
    private val database: LocalDatabase
) : FavoriteCatRepository {

    override suspend fun saveFavoriteCat(catModel: CatModel) = database.insertFavoriteCats(catModel)

    override suspend fun deleteFavoriteCat(idCat: String) = database.deleteFavoriteCat(idCat)

    override suspend fun getFavoriteCats(): UiState {
        val localFavoriteCats = database.getAllFavoritesCats()

        return if (localFavoriteCats.isNotEmpty()) {
            UiState.Success(localFavoriteCats)
        } else {
            UiState.Error("Todavía no tienes gatos favoritos")
        }
    }
}