package dev.donmanuel.kotlinandroidtemplate.data.mappers

import dev.donmanuel.kotlinandroidtemplate.data.local.BeerEntity
import dev.donmanuel.kotlinandroidtemplate.data.remote.BeerDto
import dev.donmanuel.kotlinandroidtemplate.domain.Beer

fun BeerDto.toBeerEntity(): BeerEntity {
    return BeerEntity(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = first_brewed,
        imageUrl = image_url
    )
}

fun BeerEntity.toBeer(): Beer {
    return Beer(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = firstBrewed,
        imageUrl = imageUrl
    )
}