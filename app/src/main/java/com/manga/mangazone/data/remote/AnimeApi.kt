package com.manga.mangazone.data.remote


import com.manga.mangazone.data.remote.dto.AnimeDetailDto
import com.manga.mangazone.data.remote.dto.AnimeListingDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApi {



    @GET("top/anime")
    suspend fun getMangaList(@Query("page") page: Int):AnimeListingDto

    @GET("anime/{anime_id}")
    suspend fun getAnimeDetail(@Path("anime_id")animeId :Int): AnimeDetailDto

    companion object {
        const val BASE_URL = "https://api.jikan.moe/v4/"
    }
}