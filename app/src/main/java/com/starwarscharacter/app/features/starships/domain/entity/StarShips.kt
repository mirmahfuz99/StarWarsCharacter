package com.starwarscharacter.app.features.starships.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "starShips")
data class StarShips(
    @PrimaryKey
    var id: String = "",
    val birthYear: String?,
    val eyeColor: String?,
    val gender: String?,
    val hairColor: String?,
    val height: String?,
    val name: String?,
    val skinColor: String?,
)
