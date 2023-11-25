package com.starwarscharacter.app.features.planet.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.starwarscharacter.app.core.db.StarWarsDatabase
import com.starwarscharacter.app.features.planet.data.datasource.remote.PlanetsRemoteDataSource
import com.starwarscharacter.app.features.planet.domain.entity.Planets
import com.starwarscharacter.app.features.planet.domain.repository.PlanetsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val PAGE_SIZE = 10

class PlanetsRepositoryImpl @Inject constructor(
    private val sharacterRemoteDataSource: PlanetsRemoteDataSource,
    private val starWarsDatabase: StarWarsDatabase
) : PlanetsRepository {
    override suspend fun getStarShips(): Flow<PagingData<Planets>> {
        val pagingSourceFactory = { starWarsDatabase.planetsDao().getAll() }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = PlanetRemoteMediator(
                starWarsDatabase,
                sharacterRemoteDataSource
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

}