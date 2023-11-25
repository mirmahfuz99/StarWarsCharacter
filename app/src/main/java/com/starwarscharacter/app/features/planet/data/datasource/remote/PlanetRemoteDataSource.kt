package com.starwarscharacter.app.features.planet.data.datasource.remote

import com.starwarscharacter.app.features.planet.data.model.dto.PlanetDto

interface PlanetsRemoteDataSource {
    suspend fun getPlanets(page: Int): PlanetDto
}