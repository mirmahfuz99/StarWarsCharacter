package com.starwarscharacter.app.features.character.data.datasource.remote


import com.starwarscharacter.app.core.api.StarWarsApi
import com.starwarscharacter.app.features.character.data.model.dto.CharacterDto
import javax.inject.Inject

class CharacterRemoteDataSourceImpl @Inject constructor(
    private val api: StarWarsApi
) : CharacterRemoteDataSource {
    override suspend fun getCharacter(page: Int): CharacterDto {
        return api.getCharacter(page)
    }
}