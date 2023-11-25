package com.starwarscharacter.app.features.planet.domain.usecase

import androidx.paging.PagingData
import com.starwarscharacter.app.core.BaseUseCase
import com.starwarscharacter.app.features.planet.domain.entity.Planets
import com.starwarscharacter.app.features.planet.domain.repository.PlanetsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlanetsUseCase @Inject constructor(
    private val repository: PlanetsRepository
) : BaseUseCase<Unit, Flow<PagingData<Planets>>> {
    override suspend fun execute(input: Unit): Flow<PagingData<Planets>> {
        return repository.getStarShips()
    }
}