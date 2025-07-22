package dev.donmanuel.app.catkmp.app_presentation.cat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.donmanuel.app.catkmp.domain.model.CatModel
import dev.donmanuel.app.catkmp.resources.Res
import dev.donmanuel.app.catkmp.resources.cat_description
import dev.donmanuel.app.catkmp.resources.cat_description_dog_friendly
import dev.donmanuel.app.catkmp.resources.cat_description_intelligence
import dev.donmanuel.app.catkmp.resources.cat_description_life_span
import dev.donmanuel.app.catkmp.resources.cat_description_origin
import dev.donmanuel.app.catkmp.resources.cat_description_stranger_friendly
import dev.donmanuel.app.catkmp.resources.cat_description_temperament
import dev.donmanuel.app.catkmp.resources.ic_back
import dev.donmanuel.app.catkmp.resources.ic_favorite_fill
import dev.donmanuel.app.catkmp.resources.ic_favorite_outline
import dev.donmanuel.app.catkmp.resources.ic_star_fill
import dev.donmanuel.app.catkmp.resources.ic_star_outline
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CatDetailScreen(
    navController: NavController,
    catSelected: CatModel
) {

    val catViewModel = koinViewModel<CatViewModel>()

    Column(
        modifier = Modifier.fillMaxSize()
            .statusBarsPadding()
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            IconButton(
                onClick = { navController.popBackStack() },
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_back),
                    contentDescription = null
                )
            }

            Box(modifier = Modifier.weight(1f, fill = false)) {
                Text(
                    text = catSelected.name,
                    fontSize = 20.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }

            FavoriteIcon(
                isInitiallyFavorite = catSelected.favorite,
                onToggleFavorite = { favorite ->
                    if (favorite) {
                        catViewModel.saveFavoriteCat(catSelected)
                    } else {
                        catViewModel.deleteFavoriteCat(catSelected.id)
                    }
                }
            )
        }

        KamelImage(
            { asyncPainterResource(catSelected.url) }, contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(254.dp)
                .align(Alignment.CenterHorizontally),
            alignment = Alignment.Center, contentScale = ContentScale.FillWidth,
            alpha = DefaultAlpha, contentAlignment = Alignment.Center
        )

        Column(Modifier.padding(12.dp)) {

            ItemCatColumDescription(
                stringResource(Res.string.cat_description),
                catSelected.description
            )

            ItemCatColumDescription(
                stringResource(Res.string.cat_description_temperament),
                catSelected.catDescription.temperament
            )

            ItemCatRowDescription(
                stringResource(Res.string.cat_description_origin),
                catSelected.catDescription.origin
            )

            ItemCatRowDescription(
                stringResource(Res.string.cat_description_life_span),
                catSelected.catDescription.lifeSpan
            )

            ItemCatScoreDescription(
                stringResource(Res.string.cat_description_intelligence),
                catSelected.catDescription.intelligence
            )

            ItemCatScoreDescription(
                stringResource(Res.string.cat_description_stranger_friendly),
                catSelected.catDescription.strangerFriendly
            )

            ItemCatScoreDescription(
                stringResource(Res.string.cat_description_dog_friendly),
                catSelected.catDescription.dogFriendly
            )
        }
    }
}

@Composable
fun FavoriteIcon(
    isInitiallyFavorite: Boolean,
    onToggleFavorite: (Boolean) -> Unit
) {
    var isFavorite by remember { mutableStateOf(isInitiallyFavorite) }

    IconButton(onClick = {
        isFavorite = !isFavorite
        onToggleFavorite(isFavorite)
    }) {
        Icon(
            painter = painterResource(
                if (isFavorite) Res.drawable.ic_favorite_fill
                else Res.drawable.ic_favorite_outline
            ),
            contentDescription = null,
        )
    }
}

@Composable
fun ItemCatColumDescription(title: String, value: String) {
    Column(Modifier.padding(top = 8.dp)) {
        Text(
            text = title,
            fontSize = 16.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = value,
            fontSize = 16.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun ItemCatRowDescription(title: String, value: String) {
    Row(Modifier.padding(top = 8.dp)) {
        Text(
            text = title,
            fontSize = 16.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = value,
            fontSize = 16.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun ItemCatScoreDescription(title: String, score: Int) {

    Row(
        Modifier.fillMaxWidth().padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        Row {
            for (i in 1..5) {
                Icon(
                    painter = when {
                        i <= score -> painterResource(Res.drawable.ic_star_fill)
                        else -> painterResource(Res.drawable.ic_star_outline)
                    },
                    contentDescription = null
                )
            }
        }
    }
}