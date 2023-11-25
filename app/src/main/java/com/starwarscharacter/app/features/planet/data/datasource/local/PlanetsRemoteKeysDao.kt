package com.starwarscharacter.app.features.planet.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface PlanetsRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<PlanetsRemotekeys>)

    @Query("SELECT * FROM planets_remote_keys WHERE planetsId = :planetsId")
    suspend fun remoteKeysStarShipsId(planetsId: String): PlanetsRemotekeys?

    @Query("DELETE FROM starships_remote_keys")
    suspend fun clearRemoteKeys()
}