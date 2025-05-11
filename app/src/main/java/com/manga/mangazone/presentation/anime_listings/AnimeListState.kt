package com.manga.mangazone.presentation.anime_listings

import com.manga.mangazone.domain.model.AnimeDetailModel

data class AnimeDetailState(
    var anime: AnimeDetailModel,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
)
