package com.manga.mangazone.data.local

import androidx.room.Dao
import androidx.room.Query


@Dao
interface AnimeDao {

    @Query("SELECT * FROM animelistingentity")
    suspend fun getAnimeList(): List<AnimeListingEntity>
}