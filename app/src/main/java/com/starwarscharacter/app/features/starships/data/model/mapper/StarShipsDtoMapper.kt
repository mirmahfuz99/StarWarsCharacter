package com.starwarscharacter.app.features.starships.data.model.mapper

import com.starwarscharacter.app.features.starships.data.model.dto.StarShipsResult
import com.starwarscharacter.app.features.starships.domain.entity.StarShips
import java.util.UUID

fun StarShipsResult.mapFromEntity() = StarShips(
    id = UUID.randomUUID().toString(),
    name = this.name,
    model = this.model,
    manufacturer = this.manufacturer,
    length = this.length,
    crew = this.crew,
    passengers = this.passengers,
    cargoCapacity = this.cargoCapacity,
    starshipClass = this.starshipClass,
)

fun List<StarShipsResult>.mapStarShipsFromListModel(): List<StarShips>{
    return this.map{
        it.mapFromEntity()
    }
}