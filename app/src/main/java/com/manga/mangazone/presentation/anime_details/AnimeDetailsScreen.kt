package com.manga.mangazone.presentation.anime_details

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.manga.mangazone.R
import com.manga.mangazone.presentation.components.ChipView
import com.manga.mangazone.ui.viewmodel.MangaViewModel

@Composable
fun AnimeDetailsScreen(
    mangaViewModel: MangaViewModel,
    navController: NavHostController,
    animeId: Int?
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val expanded = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.onSurface)

    ) {
        LaunchedEffect(key1 = Unit) {
            if (animeId != null) {
                mangaViewModel.getAnimeDetail(animeId)
            }
        }

        var animeDetail = mangaViewModel._animeDetail.collectAsState()
        DisposableEffect(key1 = Unit) {
            onDispose {
                mangaViewModel._animeDetail.value = null
            }
        }
        val load = mangaViewModel.loading

        if (load.value) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        animeDetail.value?.let {
            Column(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .padding(horizontal = 24.dp)
                    .verticalScroll(scrollState)
            )
            {
                Row(
                    modifier = Modifier.padding(bottom = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back",
                        tint = Color(0xFFC9B1F1),
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                navController.popBackStack()
                            }
                    )
                    //  AnimeHeading("Anime Detail")
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.onSurface)
                        .padding(bottom = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = MaterialTheme.colorScheme.onSurface)
                            .aspectRatio(16 / 9f)
                            .clip(RectangleShape)
                    ) {
                        if (it.embedUrl.isNullOrEmpty())
                            AsyncImage(
                                model = ImageRequest.Builder(context)
                                    .data(it.poster)
                                    .placeholder(R.drawable.place_holder)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "Loaded Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(),
                                contentScale = ContentScale.FillWidth,
                                alignment = Alignment.Center
                            )
                        else {

                            YouTubePlayer(it.embedUrl, true)

                        }
                    }

                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = it.title,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center,
                        color = Color(0xFFA675F5)
                    )
                    Spacer(Modifier.height(8.dp))
                    ChipView(it.rating.toString())
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = " Episodes : ${it.episodesCount}",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp,
                            ),
                            maxLines = 2,
                            textAlign = TextAlign.Center,
                            color = Color(0xFFF6ECAB)
                        )
                    }

                    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                        it.genre.forEachIndexed { index, genre ->
                            Text(
                                text = if (it.genre.size - 1 == index) genre else "$genre  |  ",
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 15.sp,
                                ),
                                maxLines = 2,
                                textAlign = TextAlign.Center,
                                color = Color(0xFFF6ECAB)
                            )


                        }
                    }


                    Column {
                        Text(
                            text = it.synopsis,
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Light,
                                fontSize = 14.sp,
                            ),
                            maxLines = if (expanded.value) Int.MAX_VALUE else 8,
                            textAlign = TextAlign.Justify,
                            color = Color.White
                        )

                        Text(
                            text = if (expanded.value) "Show less" else "Show more",
                            color = Color(0xFFA675F5),
                            modifier = Modifier
                                .clickable { expanded.value = !expanded.value }
                                .padding(top = 4.dp)
                        )
                    }

                    Text(
                        text = "Watch ON",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.ExtraBold
                        ),
                        color = Color(0xFFF6ECAB),
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                    )
                    LazyRow(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.padding(bottom = 32.dp)
                    ) {
                        item {
                            it.mainCast.forEachIndexed { index, cast ->

                                Box(
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp)
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(Color(0xFFE91E63))
                                        .padding(horizontal = 8.dp, vertical = 8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .clickable {
                                                openUrlInChromeButton(context, cast.second)
                                            },
                                        text = cast.first,
                                        style = MaterialTheme.typography.headlineSmall.copy(
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 15.sp,
                                        ),
                                        maxLines = 2,
                                        textAlign = TextAlign.Center,
                                        color = Color(0xFFF6ECAB)
                                    )
                                }


                            }
                        }

                    }


                }
            }
        }
    }

}

fun openUrlInChromeButton(context: Context, url: String) {

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
        // Optional: specifically set Chrome as the browser
        setPackage("com.android.chrome")
    }
    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        // Chrome is not installed, fall back to a generic browser
        val fallbackIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(fallbackIntent)
    }
}


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun YouTubePlayer(videoId: String, isVisible: Boolean) {

    AndroidView(modifier = Modifier, factory = { context ->
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(android.graphics.Color.TRANSPARENT)
            settings.javaScriptEnabled = true
            settings.mediaPlaybackRequiresUserGesture = false
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()

            loadUrl(videoId)
        }
    })
}
