package com.starwarscharacter.app.features.character.domain.usecase
import androidx.paging.PagingData
import com.starwarscharacter.app.core.BaseUseCase
import com.starwarscharacter.app.features.character.domain.entity.Character
import com.starwarscharacter.app.features.character.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) : BaseUseCase<Unit, Flow<PagingData<Character>>> {
    override suspend fun execute(input: Unit): Flow<PagingData<Character>> {
        return repository.getCharacters()
    }
}