package dev.donmanuel.app.catkmp.data.local

import app.cash.sqldelight.db.SqlDriver
import dev.donmanuel.app.catkmp.CatDatabase
import dev.donmanuel.app.catkmp.data.mapper.catTableToCatModel
import dev.donmanuel.app.catkmp.data.mapper.favoriteCatTableToCatModel
import dev.donmanuel.app.catkmp.domain.model.CatModel
import dev.donmanuel.app.catkmp.domain.model.SignupRequest
import dev.donmanuel.app.catkmp.domain.model.SignupResponse

interface DatabaseDriverFactory {
    /**
 * Creates and returns a new SQL driver instance for database access.
 *
 * @return A configured [SqlDriver] for database operations.
 */
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

    /**
     * Retrieves all favorite cats from the database.
     *
     * @return A list of favorite cats as `CatModel` objects.
     */
    fun getAllFavoritesCats(): List<CatModel> {
        return query.readAllFavoritesCats().executeAsList().favoriteCatTableToCatModel()
    }

    /**
     * Inserts a new user record into the database using the provided signup information and user ID.
     *
     * @param signupRequest The user's signup details.
     * @param userId The unique identifier for the user.
     */

    fun insertUser(signupRequest: SignupRequest, userId: String) {
        query.insertUser(
            id = userId,
            name = signupRequest.name,
            user = signupRequest.user,
            email = signupRequest.email,
            password = signupRequest.password
        )
    }

    /**
     * Retrieves all user records from the database and returns them as a list of `SignupResponse` objects.
     *
     * @return A list of users with their IDs, names, usernames, and emails.
     */

    fun getAllUsers(): List<SignupResponse> {
        return query.readAllUsers().executeAsList().map {
            SignupResponse(
                userId = it.id,
                name = it.name,
                user = it.user,
                email = it.email
            )
        }
    }
}