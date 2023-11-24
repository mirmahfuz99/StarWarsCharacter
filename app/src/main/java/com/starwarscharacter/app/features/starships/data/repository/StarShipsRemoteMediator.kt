package com.starwarscharacter.app.features.starships.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.starwarscharacter.app.core.db.StarWarsDatabase
import com.starwarscharacter.app.features.starships.data.datasource.local.StarShipsRemotekeys
import com.starwarscharacter.app.features.starships.data.datasource.remote.StarShipsRemoteDataSource
import com.starwarscharacter.app.features.starships.data.model.mapper.mapStarShipsFromListModel
import com.starwarscharacter.app.features.starships.domain.entity.StarShips
import retrofit2.HttpException
import java.io.IOException


@OptIn(ExperimentalPagingApi::class)
class StarShipsRemoteMediator(
    private val starWarsDatabase: StarWarsDatabase,
    private val remoteDataSource: StarShipsRemoteDataSource
) : RemoteMediator<Int, StarShips>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, StarShips>
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
            val apiResponse = remoteDataSource.getStarShips(page)

            val apiResults = apiResponse.results
            val endOfPaginationReached = apiResults.isEmpty()
            starWarsDatabase.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    starWarsDatabase.starShipsRemoteKeysDao().clearRemoteKeys()
                    starWarsDatabase.starshipsDao().clearStarShips()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1

                val starships = apiResults.mapStarShipsFromListModel()
                val keys = starships.map {
                    StarShipsRemotekeys(starShipsId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                starWarsDatabase.starShipsRemoteKeysDao().insertAll(keys)
                Log.d("StarShipsRemoteMediator", starships.toString())
                starWarsDatabase.starshipsDao().insertAll(starships)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, StarShips>,
    ): StarShipsRemotekeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                starWarsDatabase.starShipsRemoteKeysDao().remoteKeysStarShipsId(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, StarShips>,
    ): StarShipsRemotekeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character ->
                starWarsDatabase.starShipsRemoteKeysDao().remoteKeysStarShipsId(character.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, StarShips>,
    ): StarShipsRemotekeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                starWarsDatabase.starShipsRemoteKeysDao().remoteKeysStarShipsId(character.id)
            }
    }

}