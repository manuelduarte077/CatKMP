package dev.donmanuel.app.catkmp.app_presentation.core.navigation

import androidx.compose.foundation.Image
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import dev.donmanuel.app.catkmp.resources.Res
import dev.donmanuel.app.catkmp.resources.cat_favorite
import dev.donmanuel.app.catkmp.resources.cat_list
import dev.donmanuel.app.catkmp.resources.img_cat_favorites
import dev.donmanuel.app.catkmp.resources.img_cat_list
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource

sealed class BottomBarItem {
    abstract val route: String
    abstract val titleResId: StringResource
    abstract val icon: @Composable () -> Unit

    data class BottomItemCatList(
        override val route: String = SCREEN_CAT_LIST,
        override val titleResId: StringResource = Res.string.cat_list,
        override val icon: @Composable () -> Unit = {
            Icon(
                painter = painterResource(Res.drawable.img_cat_list),
                contentDescription = null
            )
        }
    ) : BottomBarItem()

    data class BottomItemCatFavorites(
        override val route: String = SCREEN_CAT_FAVORITES,
        override val titleResId: StringResource = Res.string.cat_favorite,
        override val icon: @Composable () -> Unit = {
            Image(painterResource(Res.drawable.img_cat_favorites), null)
        }
    ) : BottomBarItem()
}