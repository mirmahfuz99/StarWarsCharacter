package com.starwarscharacter.app.features.starships.domain.repository

import androidx.paging.PagingData
import com.starwarscharacter.app.features.starships.domain.entity.StarShips
import kotlinx.coroutines.flow.Flow

interface StarShipsRepository {
    suspend fun getStarShips(): Flow<PagingData<StarShips>>
}