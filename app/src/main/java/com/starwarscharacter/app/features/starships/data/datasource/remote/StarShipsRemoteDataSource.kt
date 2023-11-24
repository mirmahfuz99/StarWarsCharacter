package com.starwarscharacter.app.features.starships.data.datasource.remote

import com.starwarscharacter.app.features.starships.data.model.dto.StarShipsDto

interface StarShipsRemoteDataSource {
    suspend fun getStarShips(page: Int): StarShipsDto
}