package com.starwarscharacter.app.features.planet.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.starwarscharacter.app.core.db.StarWarsDatabase
import com.starwarscharacter.app.features.planet.data.datasource.local.PlanetsRemotekeys
import com.starwarscharacter.app.features.planet.data.datasource.remote.PlanetsRemoteDataSource
import com.starwarscharacter.app.features.planet.data.model.mapper.mapPlanetsListModel
import com.starwarscharacter.app.features.planet.domain.entity.Planets
import retrofit2.HttpException
import java.io.IOException


@OptIn(ExperimentalPagingApi::class)
class PlanetRemoteMediator(
    private val starWarsDatabase: StarWarsDatabase,
    private val remoteDataSource: PlanetsRemoteDataSource
) : RemoteMediator<Int, Planets>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Planets>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        try {
            val apiResponse = remoteDataSource.getPlanets(page)

            val apiResults = apiResponse.results
            val endOfPaginationReached = apiResults.isEmpty()
            starWarsDatabase.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    starWarsDatabase.starShipsRemoteKeysDao().clearRemoteKeys()
                    starWarsDatabase.planetsDao().clearPlanets()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1

                val starships = apiResults.mapPlanetsListModel()
                val keys = starships.map {
                    PlanetsRemotekeys(planetsId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                starWarsDatabase.planetsRemoteKeysDao().insertAll(keys)
                Log.d("StarShipsRemoteMediator", starships.toString())
                starWarsDatabase.planetsDao().insertAll(starships)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Planets>,
    ): PlanetsRemotekeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                starWarsDatabase.planetsRemoteKeysDao().remoteKeysStarShipsId(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Planets>,
    ): PlanetsRemotekeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character ->
                starWarsDatabase.planetsRemoteKeysDao().remoteKeysStarShipsId(character.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Planets>,
    ): PlanetsRemotekeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                starWarsDatabase.planetsRemoteKeysDao().remoteKeysStarShipsId(character.id)
            }
    }

}