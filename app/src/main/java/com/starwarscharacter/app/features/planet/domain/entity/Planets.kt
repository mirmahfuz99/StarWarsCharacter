package com.starwarscharacter.app.features.planet.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "starShips")
data class Planets(
    @PrimaryKey
    var id: String = "",
    val name: String?,
    val model: String?,
    val manufacturer: String?,
    val length: String?,
    val crew: String?,
    val passengers: String?,
    val cargoCapacity: String?,
    val starshipClass: String?,
)
