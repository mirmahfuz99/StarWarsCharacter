package com.starwarscharacter.app.features.planet.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "planets")
data class Planets(
    @PrimaryKey
    var id: String = "",
    var name: String?,
    val rotationPeriod: String?,
    val orbitalPeriod: String?,
    val diameter: String?,
    val climate: String?,
    val gravity: String?,
    val terrain: String?,
    val surfaceWater: String?,
    val population: String?,
)
