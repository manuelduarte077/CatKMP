package dev.donmanuel.app.catkmp.domain.model

data class CatModel(
    val id: String,
    val url: String,
    val name: String,
    val description: String,
    val catDescription: CatDescription,
    val favorite: Boolean = false
)

data class CatDescription(
    var origin: String,
    var temperament: String,
    var lifeSpan: String,
    var intelligence: Int,
    var strangerFriendly: Int,
    var dogFriendly: Int,
)