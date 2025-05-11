package com.manga.mangazone.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AnimeListingEntity::class],
    version = 1)
abstract class AnimeDatabase :RoomDatabase(){
    abstract val dao: AnimeDao
}