package com.starwarscharacter.app.core.api

import com.starwarscharacter.app.features.character.data.model.dto.CharacterDto
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsApi {

    @GET("people/")
    suspend fun getCharacter(@Query("page") page: Int): CharacterDto

}