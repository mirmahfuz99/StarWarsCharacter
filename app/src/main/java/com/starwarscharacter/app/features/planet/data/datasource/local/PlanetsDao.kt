package com.starwarscharacter.app.features.planet.data.datasource.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.starwarscharacter.app.features.planet.domain.entity.Planets

@Dao
interface PlanetsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<Planets>)

    @Query("SELECT * FROM planets")
    fun getAll(): PagingSource<Int, Planets>

    @Query("DELETE FROM planets")
    suspend fun clearPlanets()
}