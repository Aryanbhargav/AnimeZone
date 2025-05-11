package com.manga.mangazone.data.mapper

import com.manga.mangazone.data.local.AnimeListingEntity
import com.manga.mangazone.data.remote.dto.AnimeDetailDto
import com.manga.mangazone.data.remote.dto.Data
import com.manga.mangazone.domain.model.AnimeDetailModel

fun Data.toAnimeListingEntity(): AnimeListingEntity {
    return AnimeListingEntity(
        malId = mal_id,
        title = title,
        rating = score,
        poster = images.jpg.image_url,
        episodesCount = episodes
    )
}

fun AnimeDetailDto.toAnimeDetailModel(): AnimeDetailModel {

    return AnimeDetailModel(
        title= data.title,
        rating= data.score,
        poster = data.images.jpg.image_url,
        episodesCount= data.episodes,
        synopsis= data.synopsis,
        genre=data.genres.map { genre-> genre.name },
        embedUrl=if(data.trailer.embed_url!=null) data.trailer.embed_url else "",
        trailerImg = if(data.trailer.images.maximum_image_url!=null)data.trailer.images.maximum_image_url else "",
        mainCast = data.producers.map { Pair(it.name,it.url) }
    )
}
