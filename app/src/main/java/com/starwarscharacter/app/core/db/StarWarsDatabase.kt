package com.starwarscharacter.app.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.starwarscharacter.app.features.character.data.datasource.local.CharacterDao
import com.starwarscharacter.app.features.character.data.datasource.local.CharacterRemoteKeys
import com.starwarscharacter.app.features.character.data.datasource.local.CharacterRemoteKeysDao
import com.starwarscharacter.app.features.character.domain.entity.Character
import com.starwarscharacter.app.features.planet.data.datasource.local.PlanetsDao
import com.starwarscharacter.app.features.planet.data.datasource.local.PlanetsRemoteKeysDao
import com.starwarscharacter.app.features.planet.data.datasource.local.PlanetsRemotekeys
import com.starwarscharacter.app.features.planet.domain.entity.Planets
import com.starwarscharacter.app.features.starships.data.datasource.local.StarShipsDao
import com.starwarscharacter.app.features.starships.data.datasource.local.StarShipsRemoteKeysDao
import com.starwarscharacter.app.features.starships.data.datasource.local.StarShipsRemotekeys
import com.starwarscharacter.app.features.starships.domain.entity.StarShips


@Database(
    entities = [
        Character::class,
        CharacterRemoteKeys::class,

        StarShips::class,
        StarShipsRemotekeys::class,

        Planets::class,
        PlanetsRemotekeys::class],
    version = 1,
    exportSchema = false
)
abstract class StarWarsDatabase: RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun characterRemoteKeysDao(): CharacterRemoteKeysDao

    abstract fun starshipsDao(): StarShipsDao
    abstract fun starShipsRemoteKeysDao(): StarShipsRemoteKeysDao

    abstract fun planetsDao(): PlanetsDao
    abstract fun planetsRemoteKeysDao(): PlanetsRemoteKeysDao


    companion object{
        const val DATABASE_NAME = "star_wars_db"
    }
}