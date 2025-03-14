package dev.donmanuel.kotlinandroidtemplate.presentation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import dev.donmanuel.kotlinandroidtemplate.domain.Beer


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeerScreen(
    beers: LazyPagingItems<Beer>
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = beers.loadState) {
        if (beers.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (beers.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Beers",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                },
                actions = {
                    androidx.compose.material3.IconButton(onClick = { /*TODO*/ }) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    }
                },
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            if (beers.loadState.refresh is LoadState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    items(beers.itemCount) { index ->
                        val beer = beers[index]

                        if (beer != null) {
                            BeerItem(
                                beer = beer,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            )
                        }
                    }

                    item {
                        if (beers.loadState.append is LoadState.Loading) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}



