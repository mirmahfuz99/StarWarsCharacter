package com.starwarscharacter.app.core.db


import CharacterDao
import androidx.room.Database
import androidx.room.RoomDatabase
import com.starwars.app.character.data.datasource.local.CharacterRemoteKeys
import com.starwars.app.character.data.datasource.local.CharacterRemoteKeysDao
import com.starwarscharacter.app.features.character.domain.entity.Character


@Database(
    entities = [
        Character::class,
        CharacterRemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class StarWarsDatabase: RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun characterRemoteKeysDao(): CharacterRemoteKeysDao


    companion object{
        const val DATABASE_NAME = "star_wars_db"
    }
}