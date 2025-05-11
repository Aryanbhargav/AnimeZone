package com.manga.mangazone.data.remote.dto

data class Trailer(
    val embed_url: String,
    val images: com.manga.mangazone.data.remote.dto.ImagesX,
    val url: String,
    val youtube_id: String
)