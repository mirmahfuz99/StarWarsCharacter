package com.starwarscharacter.app.features.character.data.datasource.remote

import com.starwarscharacter.app.features.character.data.model.dto.CharacterDto

interface CharacterRemoteDataSource {
    suspend fun getCharacter(page: Int): CharacterDto
}