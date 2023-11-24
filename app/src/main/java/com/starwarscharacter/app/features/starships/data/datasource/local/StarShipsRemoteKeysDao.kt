package com.starwarscharacter.app.features.starships.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface StarShipsRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<StarShipsRemotekeys>)

    @Query("SELECT * FROM starships_remote_keys WHERE starShipsId = :starShipsId")
    suspend fun remoteKeysStarShipsId(starShipsId: String): StarShipsRemotekeys?

    @Query("DELETE FROM starships_remote_keys")
    suspend fun clearRemoteKeys()
}