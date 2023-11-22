package com.starwarscharacter.app.core.di

import com.starwarscharacter.app.core.api.StarWarsApi
import com.starwarscharacter.app.core.db.StarWarsDatabase
import com.starwarscharacter.app.features.character.data.datasource.remote.CharacterRemoteDataSource
import com.starwarscharacter.app.features.character.data.datasource.remote.CharacterRemoteDataSourceImpl
import com.starwarscharacter.app.features.character.data.repository.CharacterRepositoryImpl
import com.starwarscharacter.app.features.character.domain.repository.CharacterRepository
import com.starwarscharacter.app.features.character.domain.usecase.GetCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharacterModule {
    @Singleton
    @Provides
    fun providesCharacterRemoteDataSource(
        api: StarWarsApi
    ): CharacterRemoteDataSource {
        return  CharacterRemoteDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun providesCharacterRepository(
        characterRemoteDataSource: CharacterRemoteDataSource,
        starWarsDatabase: StarWarsDatabase
    ): CharacterRepository {
        return CharacterRepositoryImpl(characterRemoteDataSource, starWarsDatabase)
    }

    @Singleton
    @Provides
    fun providesGetCharactersUseCase(
        characterRepository: CharacterRepository
    ): GetCharactersUseCase {
        return GetCharactersUseCase(characterRepository)
    }
}