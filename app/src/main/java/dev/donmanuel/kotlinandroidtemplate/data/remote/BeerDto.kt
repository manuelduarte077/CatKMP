package dev.donmanuel.kotlinandroidtemplate.data.remote

/**
 * Data Transfer Object (DTO) representing a beer.
 * This class holds the data for a beer, typically received from an external source
 * like an API. It is used to transfer beer data between different layers of the application.
 *
 * @property id The unique identifier of the beer.
 * @property name The name of the beer.
 * @property tagline A short, catchy phrase associated with the beer.
 * @property description A detailed description of the beer's characteristics.
 * @property first_brewed The date when the beer was first brewed, typically in MM/YYYY format.
 * @property image_url The URL of an image representing the beer. Can be null if no image is available.
 */
data class BeerDto (
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val first_brewed: String,
    val image_url: String?
)