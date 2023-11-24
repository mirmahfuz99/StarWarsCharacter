package com.starwarscharacter.app.features.starships.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "starships_remote_keys")
data class StarShipsRemotekeys(
    @PrimaryKey val starShipsId: String,
    val prevKey: Int?,
    val nextKey: Int?
)
