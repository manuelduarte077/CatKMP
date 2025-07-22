package dev.donmanuel.app.catkmp.data.local

import app.cash.sqldelight.db.SqlDriver
import dev.donmanuel.app.catkmp.CatDatabase
import dev.donmanuel.app.catkmp.data.mapper.catTableToCatModel
import dev.donmanuel.app.catkmp.data.mapper.favoriteCatTableToCatModel
import dev.donmanuel.app.catkmp.domain.model.CatModel

interface DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

class LocalDatabase(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = CatDatabase(databaseDriverFactory.createDriver())

    private val query = database.catDatabaseQueries

    fun getAllCats(): List<CatModel> {
        return query.readAllCats().executeAsList().catTableToCatModel()
    }

    fun insertAllCats(cats: List<CatModel>) {
        query.transaction {
            cats.forEach { cat ->
                query.insertCat(
                    id = cat.id,
                    url = cat.url,
                    name = cat.name,
                    description = cat.description,
                    origin = cat.catDescription.origin,
                    temperament = cat.catDescription.temperament,
                    lifeSpan = cat.catDescription.lifeSpan,
                    intelligence = cat.catDescription.intelligence.toLong(),
                    strangerFriendly = cat.catDescription.strangerFriendly.toLong(),
                    dogFriendly = cat.catDescription.dogFriendly.toLong()
                )
            }
        }
    }

    fun removeAllCats() {
        query.deleteCats()
    }

    fun insertFavoriteCats(cat: CatModel) {
        query.transaction {
            query.insertFavoriteCat(
                id = cat.id,
                url = cat.url,
                name = cat.name,
                description = cat.description,
                origin = cat.catDescription.origin,
                temperament = cat.catDescription.temperament,
                lifeSpan = cat.catDescription.lifeSpan,
                intelligence = cat.catDescription.intelligence.toLong(),
                strangerFriendly = cat.catDescription.strangerFriendly.toLong(),
                dogFriendly = cat.catDescription.dogFriendly.toLong()
            )
        }
    }

    fun deleteFavoriteCat(idCat: String) {
        query.transaction {
            query.deleteFavoriteCat(idCat)
        }
    }

    fun getAllFavoritesCats(): List<CatModel> {
        return query.readAllFavoritesCats().executeAsList().favoriteCatTableToCatModel()
    }
}