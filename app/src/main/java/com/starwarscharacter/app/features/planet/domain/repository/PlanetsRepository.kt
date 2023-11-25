package com.starwarscharacter.app.features.planet.domain.repository

import androidx.paging.PagingData
import com.starwarscharacter.app.features.planet.domain.entity.Planets
import kotlinx.coroutines.flow.Flow

interface PlanetsRepository {
    suspend fun getPlanets(): Flow<PagingData<Planets>>
}