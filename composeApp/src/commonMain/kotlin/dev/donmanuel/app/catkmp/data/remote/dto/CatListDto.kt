package dev.donmanuel.app.catkmp.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatListDto(

    @SerialName("id")
    val id: String,

    @SerialName("url")
    val url: String,

    @SerialName("breeds")
    val breeds: List<CatBreed?>,
) {
    @Serializable
    data class CatBreed(
        @SerialName("name")
        val name: String?,

        @SerialName("description")
        val description: String?,

        @SerialName("origin")
        val origin: String?,

        @SerialName("temperament")
        val temperament: String?,

        @SerialName("life_span")
        val lifeSpan: String?,

        @SerialName("intelligence")
        val intelligence: Int?,

        @SerialName("stranger_friendly")
        val strangerFriendly: Int?,

        @SerialName("dog_friendly")
        val dogFriendly: Int?
    )
}