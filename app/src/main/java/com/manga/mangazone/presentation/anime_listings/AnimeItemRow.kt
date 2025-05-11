package com.manga.mangazone.presentation.anime_listings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.manga.mangazone.domain.model.AnimeListing
import com.manga.mangazone.R

@Composable
fun AnimeItemRow(
    AnimeListing: AnimeListing,
    onItemClick: (Int) -> Unit
) {
    val context = LocalContext.current
    val currentOnTick by rememberUpdatedState(newValue = onItemClick)
    Card(
        modifier = Modifier
            .clickable(onClick = { currentOnTick(AnimeListing.malId) })
            .background(Color.Black.copy(alpha = 0.3f))
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(20.dp))
            .padding(8.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onSurface),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(AnimeListing.poster)
                    .placeholder(R.drawable.place_holder)
                    .crossfade(true)
                    .build(),
                contentDescription = "Loaded Image",
                modifier = Modifier
                    .weight(0.3f)
                    .padding(vertical = 16.dp, horizontal = 8.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(Modifier.weight(0.5f)) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = AnimeListing.title, color = Color(0xFFA675F5), maxLines = 2,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "Rating : ${AnimeListing.rating}",
                    color = Color(0xFFF6ECAB), style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                    )
                )
                Text(
                    text = "Episodes : ${AnimeListing.episodesCount}",
                    color = Color(0xFFF6ECAB),
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                    )
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }

}
