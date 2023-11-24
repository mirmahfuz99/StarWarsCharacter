package com.starwarscharacter.app.features.starships.domain.usecase

import androidx.paging.PagingData
import com.starwarscharacter.app.core.BaseUseCase
import com.starwarscharacter.app.features.starships.domain.entity.StarShips
import com.starwarscharacter.app.features.starships.domain.repository.StarShipsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStarShipsUseCase @Inject constructor(
    private val repository: StarShipsRepository
) : BaseUseCase<Unit, Flow<PagingData<StarShips>>> {
    override suspend fun execute(input: Unit): Flow<PagingData<StarShips>> {
        return repository.getStarShips()
    }
}