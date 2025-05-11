package com.manga.mangazone.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.manga.mangazone.data.mapper.toAnimeListingEntity
import com.manga.mangazone.domain.model.AnimeListing

class AnimePagingSource(private val animeApi: AnimeApi) :PagingSource<Int,AnimeListing>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeListing> {
        val page=params.key?:1
        return try{
            val response= animeApi.getMangaList(page)
            val data= response.data.map { dataDto->
                dataDto.toAnimeListingEntity()
                AnimeListing(
                    malId = dataDto.mal_id,
                    title = dataDto.title,
                    rating = dataDto.score,
                    poster = dataDto.images.jpg.image_url,
                    episodesCount = dataDto.episodes
                )
            }
            LoadResult.Page(
                data= data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if(response.pagination.has_next_page) page+1 else null
            )
        }catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AnimeListing>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}