package com.manga.mangazone.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AnimeListingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val malId:Int,
    val title: String,
    val rating :Double,
    val poster : String,
    val episodesCount : Int

)