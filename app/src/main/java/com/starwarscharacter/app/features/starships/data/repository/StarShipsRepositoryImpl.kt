package com.starwarscharacter.app.features.starships.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.starwarscharacter.app.core.db.StarWarsDatabase
import com.starwarscharacter.app.features.starships.data.datasource.remote.StarShipsRemoteDataSource
import com.starwarscharacter.app.features.starships.domain.entity.StarShips
import com.starwarscharacter.app.features.starships.domain.repository.StarShipsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val PAGE_SIZE = 10

class StarShipsRepositoryImpl @Inject constructor(
    private val sharacterRemoteDataSource: StarShipsRemoteDataSource,
    private val starWarsDatabase: StarWarsDatabase
) : StarShipsRepository {
    override suspend fun getStarShips(): Flow<PagingData<StarShips>> {
        val pagingSourceFactory = { starWarsDatabase.starshipsDao().getAll() }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = StarShipsRemoteMediator(
                starWarsDatabase,
                sharacterRemoteDataSource
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

}