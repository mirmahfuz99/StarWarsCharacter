package com.starwarscharacter.app.core.di

import com.starwarscharacter.app.core.api.StarWarsApi
import com.starwarscharacter.app.core.db.StarWarsDatabase
import com.starwarscharacter.app.features.planet.data.datasource.remote.PlanetRemoteDataSourceImpl
import com.starwarscharacter.app.features.planet.data.datasource.remote.PlanetsRemoteDataSource
import com.starwarscharacter.app.features.planet.data.repository.PlanetsRepositoryImpl
import com.starwarscharacter.app.features.planet.domain.repository.PlanetsRepository
import com.starwarscharacter.app.features.planet.domain.usecase.GetPlanetsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlanetsModule {
    @Singleton
    @Provides
    fun providesPlanetsRemoteDataSource(
        api: StarWarsApi
    ): PlanetsRemoteDataSource {
        return  PlanetRemoteDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun providesPlanetsRepository(
        characterRemoteDataSource: PlanetsRemoteDataSource,
        starWarsDatabase: StarWarsDatabase
    ): PlanetsRepository {
        return PlanetsRepositoryImpl(characterRemoteDataSource, starWarsDatabase)
    }

    @Singleton
    @Provides
    fun providesGetStarShipsUseCase(
        characterRepository: PlanetsRepository
    ): GetPlanetsUseCase {
        return GetPlanetsUseCase(characterRepository)
    }
}