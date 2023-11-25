package com.starwarscharacter.app.features.planet.data.datasource.remote

import com.starwarscharacter.app.core.api.StarWarsApi
import com.starwarscharacter.app.features.planet.data.model.dto.PlanetDto
import javax.inject.Inject

class PlanetRemoteDataSourceImpl @Inject constructor(
    private val api: StarWarsApi
) : PlanetsRemoteDataSource {
    override suspend fun getPlanets(page: Int): PlanetDto {
        return api.getPlanets(page)
    }
}