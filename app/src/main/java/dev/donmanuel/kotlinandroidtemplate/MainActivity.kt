package dev.donmanuel.kotlinandroidtemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import dagger.hilt.android.AndroidEntryPoint
import dev.donmanuel.kotlinandroidtemplate.presentation.BeerScreen
import dev.donmanuel.kotlinandroidtemplate.presentation.BeerViewModel
import dev.donmanuel.kotlinandroidtemplate.ui.theme.KotlinAndroidTemplateTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinAndroidTemplateTheme {
                val viewModel = hiltViewModel<BeerViewModel>()
                val beers = viewModel.beerPagingFlow.collectAsLazyPagingItems()
                
                BeerScreen(beers = beers)
            }
        }
    }
}
