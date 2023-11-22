package com.starwarscharacter.app.features.character.domain.repository

import androidx.paging.PagingData
import com.starwarscharacter.app.features.character.domain.entity.Character
import kotlinx.coroutines.flow.Flow
interface CharacterRepository {
    suspend fun getCharacters(): Flow<PagingData<Character>>
}