package com.starwarscharacter.app.core.api

import com.starwarscharacter.app.features.character.data.model.dto.CharacterDto
import com.starwarscharacter.app.features.planet.data.model.dto.PlanetDto
import com.starwarscharacter.app.features.starships.data.model.dto.StarShipsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsApi {

    @GET("people/")
    suspend fun getCharacter(@Query("page") page: Int): CharacterDto



    @GET("starships/")
    suspend fun getStarShips(@Query("page") page: Int): StarShipsDto

    @GET("planets/")
    suspend fun getPlanets(@Query("page") page: Int): PlanetDto

}