package com.manga.mangazone.domain.model

data class AnimeDetailModel(
    val title: String,
    val rating :Double,
    val poster : String,
    val episodesCount : Int,
    val synopsis : String,
    val genre: List<String>,
    val embedUrl :String,
    val trailerImg :String,
    val mainCast : List<Pair<String,String>>
)
