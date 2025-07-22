package dev.donmanuel.app.catkmp.data.remote.repository

import dev.donmanuel.app.catkmp.data.local.LocalDatabase
import dev.donmanuel.app.catkmp.data.mapper.catDtoToCatModel
import dev.donmanuel.app.catkmp.data.remote.KtorUtils
import dev.donmanuel.app.catkmp.data.remote.dto.CatListDto
import dev.donmanuel.app.catkmp.domain.repository.CatRepository
import dev.donmanuel.app.catkmp.domain.repository.UiState
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.utils.io.errors.IOException

const val keyCat = "live_iZiWd3boNh2rNBGQ03JNUAsMSCP4BW26L2gxmqAR1DUsKVfnIyT3ouzV7xJM5P1r"
const val catUrlList =
    "https://api.thecatapi.com/v1/images/search?has_breeds=1&limit=20&api_key=${keyCat}"


class CatRepositoryData(
    private val database: LocalDatabase
) : CatRepository {

    override suspend fun getCatList(): UiState {
        return try {
            val response = KtorUtils.httpClient.get(catUrlList).body<List<CatListDto>>()
            val catList = response.catDtoToCatModel()

            database.removeAllCats()
            database.insertAllCats(catList)

            UiState.Success(catList)
        } catch (e: Exception) {
            e.printStackTrace()

            return if (e is IOException || e.message?.contains("Unable to resolve host") == true) {
                val localCats = database.getAllCats()
                if (localCats.isNotEmpty()) {
                    UiState.Success(localCats)
                } else {
                    UiState.Error("Sin conexión y sin datos locales.")
                }
            } else {
                UiState.Error("Error: ${e.message}")
            }
        }
    }
}