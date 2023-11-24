package com.starwarscharacter.app.core.di

import com.starwarscharacter.app.core.api.StarWarsApi
import com.starwarscharacter.app.core.db.StarWarsDatabase
import com.starwarscharacter.app.features.starships.data.datasource.remote.StarShipsRemoteDataSource
import com.starwarscharacter.app.features.starships.data.datasource.remote.StarShipsRemoteDataSourceImpl
import com.starwarscharacter.app.features.starships.data.repository.StarShipsRepositoryImpl
import com.starwarscharacter.app.features.starships.domain.repository.StarShipsRepository
import com.starwarscharacter.app.features.starships.domain.usecase.GetStarShipsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StarShipsModule {
    @Singleton
    @Provides
    fun providesStarShipsRemoteDataSource(
        api: StarWarsApi
    ): StarShipsRemoteDataSource {
        return  StarShipsRemoteDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun providesStarShipsRepository(
        characterRemoteDataSource: StarShipsRemoteDataSource,
        starWarsDatabase: StarWarsDatabase
    ): StarShipsRepository {
        return StarShipsRepositoryImpl(characterRemoteDataSource, starWarsDatabase)
    }

    @Singleton
    @Provides
    fun providesGetStarShipsUseCase(
        characterRepository: StarShipsRepository
    ): GetStarShipsUseCase {
        return GetStarShipsUseCase(characterRepository)
    }
}