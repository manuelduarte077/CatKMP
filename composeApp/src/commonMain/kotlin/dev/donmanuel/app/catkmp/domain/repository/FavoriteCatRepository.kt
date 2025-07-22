package dev.donmanuel.app.catkmp.domain.repository

import dev.donmanuel.app.catkmp.domain.model.CatModel

interface FavoriteCatRepository {

    suspend fun saveFavoriteCat(catModel: CatModel)

    suspend fun deleteFavoriteCat(idCat: String)

    suspend fun getFavoriteCats(): UiState
}