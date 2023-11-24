package com.starwarscharacter.app.features.starships.data.model.mapper

import com.starwarscharacter.app.features.starships.data.model.dto.StarShipsResult
import com.starwarscharacter.app.features.starships.domain.entity.StarShips
import java.util.UUID

fun StarShipsResult.mapFromEntity() = StarShips(
    id = UUID.randomUUID().toString(),
    name = this.name,
    model = this.model,
    manufacturer = this.manufac,
    costInCredits = this.hairColor,
    length = this.height,
    crew = this.name,
    passengers = this.name,
    cargoCapacity = this.name,
    gmlt = this.name,
    starshipClass = this.name,
)

fun List<StarShipsResult>.mapStarShipsFromListModel(): List<StarShips>{
    return this.map{
        it.mapFromEntity()
    }
}