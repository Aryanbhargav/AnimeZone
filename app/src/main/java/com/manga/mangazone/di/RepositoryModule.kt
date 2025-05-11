package com.manga.mangazone.di

import com.manga.mangazone.data.repository.AnimeRepositoryImpl
import com.manga.mangazone.domain.repository.AnimeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAnimeRepository(
        AnimeRepositoryImpl: AnimeRepositoryImpl
    ): AnimeRepository
}