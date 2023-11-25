package com.starwarscharacter.app.features.planet.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "planets_remote_keys")
data class PlanetsRemotekeys(
    @PrimaryKey val planetsId: String,
    val prevKey: Int?,
    val nextKey: Int?
)
