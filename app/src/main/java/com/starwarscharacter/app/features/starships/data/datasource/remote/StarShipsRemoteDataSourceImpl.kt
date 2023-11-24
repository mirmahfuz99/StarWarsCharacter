package com.starwarscharacter.app.features.starships.data.datasource.remote

import com.starwarscharacter.app.core.api.StarWarsApi
import com.starwarscharacter.app.features.starships.data.model.dto.StarShipsDto
import javax.inject.Inject

class StarShipsRemoteDataSourceImpl @Inject constructor(
    private val api: StarWarsApi
) : StarShipsRemoteDataSource {
    override suspend fun getStarShips(page: Int): StarShipsDto {
        return api.getStarShips(page)
    }
}