package com.manga.mangazone.presentation.anime_listings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.manga.mangazone.presentation.components.AnimeHeading
import com.manga.mangazone.ui.viewmodel.MangaViewModel

@Composable
fun AnimeListingScreen(
    navHostController: NavHostController,
    mangaViewModel: MangaViewModel
) {
    val items = mangaViewModel.items.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.onSurface),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimeHeading("AnimeZone")
        }
        LazyColumn(
            Modifier
                .background(color = MaterialTheme.colorScheme.onSurface)
        ) {
            items(items.itemCount) { manga ->
                val item = items[manga]
                AnimeItemRow(
                    item!!,
                ) {
                    navHostController.currentBackStackEntry?.savedStateHandle?.apply {
                        set("AnimeId", it)
                    }
                    navHostController.navigate("manga_details_screen")
                }
            }
            if (items.loadState.append is LoadState.Loading) {
                item {
                    Box(Modifier.fillMaxWidth()) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}


