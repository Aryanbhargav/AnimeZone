package com.manga.mangazone.domain.repository

import androidx.paging.PagingData
import com.manga.mangazone.domain.model.AnimeDetailModel
import com.manga.mangazone.domain.model.AnimeListing
import com.manga.mangazone.util.Resource
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    fun getMangaListing(fetchFromRemote: Boolean): Flow<PagingData<AnimeListing>>
    suspend fun getAnimeDetail(animeId: Int): Flow<Resource<AnimeDetailModel>>
}