package dev.donmanuel.app.catkmp.data.mapper

import dev.donmanuel.app.catkmp.CatTable
import dev.donmanuel.app.catkmp.FavoriteCatsTable
import dev.donmanuel.app.catkmp.data.remote.dto.CatListDto
import dev.donmanuel.app.catkmp.domain.model.CatDescription
import dev.donmanuel.app.catkmp.domain.model.CatModel

fun List<CatListDto>.catDtoToCatModel() = map {

    if (it.breeds.isEmpty()) {
        CatModel(
            id = it.id,
            url = it.url,
            name = "No name",
            description = "No description",
            catDescription = CatDescription(
                origin = "No origin",
                temperament = "No temperament",
                lifeSpan = "No lifeSpan",
                intelligence = 0,
                strangerFriendly = 0,
                dogFriendly = 0
            )
        )
    } else {
        CatModel(
            id = it.id,
            url = it.url,
            name = it.breeds[0]?.name ?: "No name",
            description = it.breeds[0]?.description ?: "No description",
            catDescription = CatDescription(
                origin = it.breeds[0]?.origin ?: "No origin",
                temperament = it.breeds[0]?.temperament ?: "No temperament",
                lifeSpan = it.breeds[0]?.lifeSpan ?: "No lifeSpan",
                intelligence = it.breeds[0]?.intelligence ?: 0,
                strangerFriendly = it.breeds[0]?.strangerFriendly ?: 0,
                dogFriendly = it.breeds[0]?.dogFriendly ?: 0
            )
        )
    }
}

fun List<CatTable>.catTableToCatModel() = map {

    CatModel(
        id = it.id,
        url = it.url,
        name = it.name,
        description = it.description,
        catDescription = CatDescription(
            origin = it.origin,
            temperament = it.temperament,
            lifeSpan = it.lifeSpan,
            intelligence = it.intelligence.toInt(),
            strangerFriendly = it.strangerFriendly.toInt(),
            dogFriendly = it.dogFriendly.toInt(),
        )
    )
}

fun List<FavoriteCatsTable>.favoriteCatTableToCatModel() = map {

    CatModel(
        id = it.id,
        url = it.url,
        name = it.name,
        description = it.description,
        catDescription = CatDescription(
            origin = it.origin,
            temperament = it.temperament,
            lifeSpan = it.lifeSpan,
            intelligence = it.intelligence.toInt(),
            strangerFriendly = it.strangerFriendly.toInt(),
            dogFriendly = it.dogFriendly.toInt(),
        ),
        favorite = true
    )
}