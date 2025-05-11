package com.manga.mangazone.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.manga.mangazone.data.local.AnimeDatabase
import com.manga.mangazone.data.mapper.toAnimeDetailModel

import com.manga.mangazone.data.remote.AnimeApi
import com.manga.mangazone.data.remote.AnimePagingSource
import com.manga.mangazone.domain.model.AnimeDetailModel
import com.manga.mangazone.domain.model.AnimeListing
import com.manga.mangazone.domain.repository.AnimeRepository
import com.manga.mangazone.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeRepositoryImpl @Inject constructor(
    private val animeApi: AnimeApi,
    private val db: AnimeDatabase
) : AnimeRepository {
    override fun getMangaListing(fetchFromRemote: Boolean): Flow<PagingData<AnimeListing>> {
        return Pager(
            config = PagingConfig(pageSize = 25),
            pagingSourceFactory = { AnimePagingSource(animeApi) }
        ).flow
    }

    override suspend fun getAnimeDetail(animeId: Int): Flow<Resource<AnimeDetailModel>> {
        return flow {
            emit(Resource.Loading(true))
            val remoteAnimeDetailResponse = try {
                animeApi.getAnimeDetail(animeId = animeId)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }
            remoteAnimeDetailResponse?.let {
                emit(
                    Resource.Success(
                        data =
                        it.toAnimeDetailModel()
                    )
                )
            }
        }
    }
}