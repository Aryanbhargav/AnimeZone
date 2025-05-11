package com.manga.mangazone.di

import android.app.Application
import androidx.room.Room
import com.manga.mangazone.data.local.AnimeDatabase
import com.manga.mangazone.data.remote.AnimeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Provides
    @Singleton
    fun provideAnimeApi(): AnimeApi {
        return Retrofit.Builder()
            .baseUrl(AnimeApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }).build())
            .build()
            .create()
    }
    @Provides
    @Singleton
    fun provideAnimeDatabase(app:Application): AnimeDatabase {
        return Room.databaseBuilder(
            app,
            AnimeDatabase::class.java,
            "mangadb.db"
        )
            .build()
    }
}