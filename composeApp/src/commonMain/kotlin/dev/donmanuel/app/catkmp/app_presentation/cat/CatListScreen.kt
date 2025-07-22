package dev.donmanuel.app.catkmp.app_presentation.cat

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.donmanuel.app.catkmp.app_presentation.core.navigation.SCREEN_CAT_DETAIL
import dev.donmanuel.app.catkmp.app_presentation.core.navigation.SCREEN_LOGIN
import dev.donmanuel.app.catkmp.data.local.SettingsUtils
import dev.donmanuel.app.catkmp.data.local.SettingsUtils.KEY_TOKEN
import dev.donmanuel.app.catkmp.data.local.repository.CatLocalRepository
import dev.donmanuel.app.catkmp.domain.model.CatModel
import dev.donmanuel.app.catkmp.domain.repository.UiState
import dev.donmanuel.app.catkmp.resources.Res
import dev.donmanuel.app.catkmp.resources.cat_list_refresh
import dev.donmanuel.app.catkmp.resources.img_cat
import dev.donmanuel.app.catkmp.resources.loading
import dev.donmanuel.app.catkmp.resources.login_out

import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CatListScreen(navController: NavController) {

    // ViewModel
    val catViewModel = koinViewModel<CatViewModel>()
    val stateCatList by catViewModel.stateCatList.collectAsState()

    LaunchedEffect(Unit) {
        catViewModel.getCats()
    }

    Column {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                modifier = Modifier.padding(16.dp).clickable {

                    SettingsUtils.data.remove(KEY_TOKEN)
                    navController.navigate(SCREEN_LOGIN) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                text = stringResource(Res.string.login_out),
                textDecoration = TextDecoration.Underline
            )

            if (stateCatList is UiState.Success || stateCatList is UiState.Error) {
                Text(
                    modifier = Modifier.padding(16.dp).clickable {
                        catViewModel.hasLoaded = false
                        catViewModel.getCats()
                    },
                    text = stringResource(Res.string.cat_list_refresh),
                    textDecoration = TextDecoration.Underline
                )
            }
        }

        when (stateCatList) {
            UiState.Init -> {}
            UiState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(stringResource(Res.string.loading))
                    CircularProgressIndicator(Modifier.padding(top = 12.dp), strokeWidth = 4.dp)
                }
            }

            is UiState.Success -> {
                val catModel =
                    ((stateCatList as UiState.Success).result as List<CatModel>)
                buildCats(catModel, navController)
            }

            is UiState.Error -> {
                Text("Error: ${(stateCatList as UiState.Error).message}")
            }
        }
    }
}

@Composable
fun buildCats(cats: List<CatModel>, navController: NavController) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        items(cats) { cat ->
            val shape: Shape = RoundedCornerShape(
                topStart = 50.dp,
                topEnd = 18.dp,
                bottomEnd = 18.dp,
                bottomStart = 50.dp
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(12.dp)
                    .clip(shape)
                    .clickable {
                        CatLocalRepository.selectCat(cat)
                        navController.navigate(SCREEN_CAT_DETAIL)
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                KamelImage(
                    resource = asyncPainterResource(cat.url),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    onLoading = {
                        CircularProgressIndicator()
                    },
                    onFailure = {
                        Image(
                            painter = painterResource(Res.drawable.img_cat),
                            contentDescription = null,
                            modifier = Modifier.size(100.dp),
                            contentScale = ContentScale.Crop
                        )
                    },
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                )

                Column(modifier = Modifier.weight(1f).padding(horizontal = 8.dp)) {

                    Text(
                        text = cat.name,
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Text(
                        text = cat.description,
                        fontSize = 14.sp,
                        lineHeight = 14.sp,
                        fontWeight = FontWeight.Normal,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}