package com.starwarscharacter.app.features.starships.data.datasource.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.starwarscharacter.app.features.starships.domain.entity.StarShips

@Dao
interface StarShipsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<StarShips>)

    @Query("SELECT * FROM characters")
    fun getAll(): PagingSource<Int, StarShips>

    @Query("DELETE FROM characters")
    suspend fun clearCharacters()
}